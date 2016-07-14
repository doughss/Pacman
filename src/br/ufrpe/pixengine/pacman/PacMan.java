package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.components.Collider;
import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.components.ObjectManager;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Input;
import br.ufrpe.pixengine.core.Renderer;
import br.ufrpe.pixengine.core.fx.AnimatedImage;
import br.ufrpe.pixengine.core.fx.SoundClip;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import java.lang.Math;

public class PacMan extends GameObject{
	 private AnimatedImage pacman;
	 private KeyCode direction;
	 private KeyCode next_direction;
	 int maze_matrice[][];
	 SoundClip eating_blue_point_sound;
	 SoundClip eating_purple_point_sound;
	 private int speed;

	 public PacMan(float x, float y, String tag, ObjectManager manager, int maze_matrice[][]) {
		setTag(tag);
		
		this.eating_blue_point_sound = new SoundClip("pacman_chomp_blue_point.wav");
		this.eating_purple_point_sound = new SoundClip("pacman_chomp_purple_point.wav");
		
		this.x = x;
		this.y = y;
		
		this.w = 36;
		this.h = 36;
		
		this.speed = 240; // pode ser 40, 80, 120, 160, 240, 360, 480 será configurável
		
		this.direction = KeyCode.RIGHT;
		this.pacman = new AnimatedImage("images/pacman_sprites.png", 400, 4, 4, 0, 0, 36, 36);
		
		this.manager = manager;
		this.maze_matrice = maze_matrice;
		
		addComponent(new Collider());
	}

	
	/**
	 * Função que diz se alguma tecla  direcional foi pressionada
	 * 
	 * @param game_input
	 */
	private boolean wasAKeyPressed(Input game_input){
		if (game_input.isKeyPressed(KeyCode.UP.ordinal())   || 
			game_input.isKeyPressed(KeyCode.LEFT.ordinal()) ||
			game_input.isKeyPressed(KeyCode.DOWN.ordinal()) ||
			game_input.isKeyPressed(KeyCode.RIGHT.ordinal())){
			return true;
		}
		return false;
	}
	

	/**
	 * Função que diz qual tecla foi pressionada
	 * 
	 * @param game_input
	 */
	private KeyCode pressedKey(Input game_input){
		if (game_input.isKeyPressed(KeyCode.RIGHT.ordinal())) {
			return KeyCode.RIGHT;
		}
		else if (game_input.isKeyPressed(KeyCode.DOWN.ordinal())) {
			return KeyCode.DOWN; 
		}
		else if (game_input.isKeyPressed(KeyCode.LEFT.ordinal())) {
			return KeyCode.LEFT;
		}
		else {
			return KeyCode.UP;
		}
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
	 * Função que checa se o pacman pode se mover pro lado direito.
	 * 
	 * @return
	 */
	private boolean canGoToTheRight(){
		int row    = (int) Math.floor(y/h);
		int column = (int) Math.floor(x/w) + 1;
		
		return isAnAvailableTile(row, column);
	}
	
	
	/**
	 * Função que checa se o pacman pode descer.
	 * 
	 * @return
	 */
	private boolean canGoDown(){
		int row    = (int) Math.floor(y/h) + 1;
		int column = (int) Math.floor(x/w);
		
		return isAnAvailableTile(row, column);
	}
	
	
	/**
	 * Função que checa se o pacman pode se mover pro lado esquerdo.
	 * 
	 * @return
	 */
	private boolean canGoToTheLeft(){
		int row    = (int) Math.floor(y/h);
		int column = (int) Math.ceil(x/w) - 1;
		
		return isAnAvailableTile(row, column);
	}
	
	
	/**
	 * Função que checa se o pacman pode subir.
	 * 
	 * @return
	 */
	private boolean canGoUp(){
		int row    = (int) Math.ceil(y/h) - 1;
		int column = (int) Math.floor(x/w);
		
		return isAnAvailableTile(row, column);
	}
	
	
	/**
	 * Função que checa se o pacman pode ou não mudar de direção
	 * em dado momento
	 * 
	 * @param direction
	 */
	private boolean canChangeDirection() {
		if (((direction == KeyCode.RIGHT || direction == KeyCode.LEFT) &&  
			(next_direction == KeyCode.RIGHT || next_direction == KeyCode.LEFT)) ||
		   ((direction == KeyCode.UP || direction == KeyCode.DOWN) &&  
					(next_direction == KeyCode.UP || next_direction == KeyCode.DOWN))){
			return true;
		}
					
		else if (x % 36 == 0 && y % 36 == 0) {
			if (next_direction == KeyCode.RIGHT) {
				return canGoToTheRight();
			}
			else if (next_direction == KeyCode.DOWN) {
				return canGoDown();
			}
			else if (next_direction == KeyCode.LEFT) {
				return canGoToTheLeft();
			}
			else if (next_direction == KeyCode.UP) {
				return canGoUp();
			}
		}
		return false;
	}
	
	
	/**
	 * Função para atualizar a direção do pacman
	 * 
	 * @param key_code
	 */
	private void updateDiretion(KeyCode pressed_key){
		direction = pressed_key;

		if (pressed_key == KeyCode.RIGHT) { 
			pacman.setOffSetY(0);
		}
		else if (pressed_key == KeyCode.DOWN) { 
			pacman.setOffSetY(36);
		}	
		else if (pressed_key == KeyCode.LEFT) { 
			pacman.setOffSetY(72);
		}
		else if (pressed_key == KeyCode.UP) {
			pacman.setOffSetY(108);
		}	
		
	}
	
	
	/**
	 * Função que movimenta o pacman.
	 * 
	 * @param displacement
	 */
	private void movingPacman(float displacement){
		if (direction == KeyCode.RIGHT) {
			if (canGoToTheRight()){
				setX(x + displacement);
				setY(y);
			}
		}
		else if (direction == KeyCode.DOWN) {
			if(canGoDown()){
				setX(x);
				setY(y + displacement);
			}
		} 
		else if (direction == KeyCode.LEFT) {
			if(canGoToTheLeft()){
				setX(x - displacement);
				setY(y);
			}
		}
		else if (direction == KeyCode.UP) {
			if(canGoUp()){
				setX(x); 
				setY(y - displacement);
			}
		}
		
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
		Input game_input = gc.getInput();
		
		if (wasAKeyPressed(game_input)){
			next_direction = pressedKey(game_input);
		}
		
		else if (next_direction != null && canChangeDirection() ){
			updateDiretion(next_direction);
			movingPacman(dt * speed);
			next_direction = null;
		}
		else{
			movingPacman(dt * speed);
		}
		
		checkBoundaries(gc);
		pacman.nextFrame(dt);
		updateComponents(gc, dt);
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		Rectangle2D pos = pacman.getCurrentFramePosition();
	    r.drawImage(pacman.getImage(), pos.getMinX(), pos.getMinY(), 
	    			pos.getWidth(), pos.getHeight(), x, y, 
	            	pos.getWidth(), pos.getHeight());
	}
	
	@Override
	public void dispose() {}
	@Override
	public void componentEvent(String name, GameObject object) {
		if (name.equalsIgnoreCase("collider")) {
			if(object instanceof BluePoint){
				manager.removePoints(object.getTag());
				eating_blue_point_sound.play();
			} else if(object instanceof PurplePoint){
				manager.removePoints(object.getTag());
				eating_purple_point_sound.play();
			}
		}
	}
}
