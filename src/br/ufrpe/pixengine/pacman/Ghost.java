package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.components.Collider;
import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.components.ObjectManager;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;
import br.ufrpe.pixengine.core.fx.AnimatedImage;
import br.ufrpe.pixengine.core.fx.SoundClip;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import java.lang.Math;
import java.util.Hashtable;

public class Ghost extends GameObject{
	 private AnimatedImage ghost;
	 private KeyCode direction;
	 int maze_matrice[][];
	 private int speed;
	 public boolean is_weak;
	 public boolean is_hidden;
	 private float x0;
	 private float y0;
	 private int offsetY;
	 private float weakness_time;
	 private float hidden_time;
	 private SoundClip has_been_eaten_sound = new SoundClip("ghost_has_been_eaten.wav");
	 public SoundClip getting_strong = new SoundClip("ghost_strong.wav");
	 
	 public Ghost(float x, float y, int offsetY, String tag, ObjectManager manager, int maze_matrice[][]) {
		setTag(tag);
		
		this.x = x;
		this.y = y;
		
		this.x0 = x;
		this.y0 = y;
		
		this.w = 36;
		this.h = 36;
		this.offsetY = offsetY;
		
		this.speed = 160; // pode ser 40, 80, 120, 160, 240, 360, 480 será configurável
		
		this.direction = KeyCode.RIGHT;

		this.ghost = new AnimatedImage("images/ghost.png", 400, 2, 2, 0, offsetY, 36, 36);
		
		this.manager = manager;
		this.maze_matrice = maze_matrice;
		
		addComponent(new Collider());
	}
	
	/**
	 * Função que checa se um tile é disponível ou não para ser percorrido.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean isAnAvailableTile(int row, int column){
		if (maze_matrice[row][column] == 1){
			return true;
		}	
		return false;
	}
	
	
	/**
	 * Função que checa se o fantasma pode se mover pro lado direito.
	 * 
	 * @return
	 */
	private boolean canGoToTheRight(){
		int row    = (int) Math.floor(y/h);
		int column = (int) Math.floor(x/w) + 1;
		
		return isAnAvailableTile(row, column);
	}
	
	
	/**
	 * Função que checa se o fantasma pode descer.
	 * 
	 * @return
	 */
	private boolean canGoDown(){
		int row    = (int) Math.floor(y/h) + 1;
		int column = (int) Math.floor(x/w);
		
		return isAnAvailableTile(row, column);
	}
	
	
	/**
	 * Função que checa se o fantasma pode se mover pro lado esquerdo.
	 * 
	 * @return
	 */
	private boolean canGoToTheLeft(){
		int row    = (int) Math.floor(y/h);
		int column = (int) Math.ceil(x/w) - 1;
		
		return isAnAvailableTile(row, column);
	}
	
	
	/**
	 * Função que checa se o fantasma pode subir.
	 * 
	 * @return
	 */
	private boolean canGoUp(){
		int row    = (int) Math.ceil(y/h) - 1;
		int column = (int) Math.floor(x/w);
		
		return isAnAvailableTile(row, column);
	}


	/**
	 * Função que movimenta o fantasma.
	 * 
	 * @param displacement
	 */
	private void movingGhost(float displacement){
		if (direction == KeyCode.RIGHT && canGoToTheRight()) {
			setX(x + displacement);
			setY(y);
		}
		else if (direction == KeyCode.DOWN && canGoDown()) {
			setX(x);
			setY(y + displacement);
		} 
		else if (direction == KeyCode.LEFT && canGoToTheLeft()) {
			setX(x - displacement);
			setY(y);
		}
		else if (direction == KeyCode.UP && canGoUp()) {
			setX(x); 
			setY(y - displacement);
		}
		else{
			changeToAnAvailableDirection();
		}
	}
	
	
	private void changeToAnAvailableDirection() {
		
		Hashtable available_direction = new Hashtable();
	
		int count = 0 ;
		if (canGoToTheRight() && (direction != KeyCode.LEFT)) {
			available_direction.put(count, KeyCode.RIGHT);
			count++;
		}
		if (canGoDown() && (direction != KeyCode.UP)) {
			available_direction.put(count, KeyCode.DOWN);
			count++;
		} 
		if (canGoToTheLeft() && (direction != KeyCode.RIGHT) ) {
			available_direction.put(count, KeyCode.LEFT);
			count++;
		}
		if (canGoUp() && (direction != KeyCode.DOWN)) {
			available_direction.put(count, KeyCode.UP);
		}
		int index = (int)(Math.random() * (available_direction.size()));
		direction = (KeyCode) available_direction.get(index);
	}
	
	
	/**
	 * Função para atualizar a posição do pacman caso ele
	 * passe por um portal
	 * 
	 * @param gc
	 */
	private void checkBoundaries(GameContainer gc){
		float x_end = (float) (gc.getWidth() - this.w);	
		if (this.x <= 0){
			this.setX(x_end);
		}else if (this.x >= x_end) {
			this.setX(0);
		}
		
		float y_end = (float) (gc.getHeight() - this.h); 
		if (this.y < 0){
			this.setY(y_end);
		}else if (this.y > y_end) {
			this.setY(0);
		}
	}
	
	
	@Override
	public void update(GameContainer gc, float dt) {
		if(is_weak){
			weakness_time += dt;
			if (weakness_time >= 5 && weakness_time <= 8 && !getting_strong.isRunning() ){
				getting_strong.play();
			}
			if (weakness_time >= 6){
				get_strong();
			}
		}
		if(is_hidden){
			hidden_time += dt;
			if (hidden_time >= 3){
				show_ghost();
			}
		}
		else{
			movingGhost(dt * speed);
		}
		
		checkBoundaries(gc);
		ghost.nextFrame(dt);
		updateComponents(gc, dt);
	}
	
	public void get_weak() {
		ghost.setOffSetY(180);
		speed = 120;
		is_weak = true;
	}
	
	public void get_strong() {
		ghost.setOffSetY(offsetY);
		speed = 160;
		is_weak=false;
		weakness_time = 0;
	}
	
	public void has_been_eaten() {
		has_been_eaten_sound.play();
		ghost.setOffSetY(-36);
		is_hidden=true;
		weakness_time = 0;
		w=0;
		h=0;
		
	}
	
	public void show_ghost() {
		x = x0;
		y = y0;
		is_hidden=false;
		is_weak=false;
		ghost.setOffSetY(this.offsetY);
		hidden_time =0;
		w=36;
		h=36;
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		Rectangle2D pos = ghost.getCurrentFramePosition();
	    r.drawImage(ghost.getImage(), pos.getMinX(), pos.getMinY(), 
	    			pos.getWidth(), pos.getHeight(), x, y, 
	            	pos.getWidth(), pos.getHeight());
	}
	
	@Override
	public void dispose() {}
	@Override
	public void componentEvent(String name, GameObject object) {
	}
}
