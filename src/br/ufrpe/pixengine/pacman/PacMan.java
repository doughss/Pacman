package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.components.Collider;
import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.components.ObjectManager;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Input;
import br.ufrpe.pixengine.core.Renderer;
import br.ufrpe.pixengine.core.fx.AnimatedImage;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import java.lang.Math;

public class PacMan extends GameObject{
	 private AnimatedImage pacman;
	 private KeyCode direction;
	 private KeyCode next_direction;
	 int maze_matrice[][];
	 private int speed;

	 public PacMan(float x, float y, String tag, ObjectManager manager, int maze_matrice[][]) {
		 setTag(tag);
		
		this.x = x;
		this.y = y;
		
		this.w = 36;
		this.h = 36;
		
		this.speed = 240; // pode ser 40, 80, 120, 160, 240, 360, 480 será configurável
		
		this.direction = KeyCode.RIGHT;
		this.pacman = new AnimatedImage("pacman/images/pacman_sprites.png", 400, 4, 4, 0, 0, 36, 36);
		
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
	
	
	/**Função que checa se um tile é disponível ou não para ser percorrido.
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
	 * Função que checa se o pacman pode continuar a se mover pro lado
	 * direito.
	 * 
	 * @return
	 */
	private boolean canKeepGoingToTheRightSide(){
		int row    = (int) Math.floor(y/h);
		int column = (int) Math.floor(x/w) + 1;
		
		return isAnAvailableTile(row, column);
	}
	
	
	/**
	 * Função que checa se o pacman pode continuar a descer.
	 * 
	 * @return
	 */
	private boolean canKeepGoingDown(){
		int row    = (int) Math.floor(y/h) + 1;
		int column = (int) Math.floor(x/w);
		
		return isAnAvailableTile(row, column);
	}
	
	
	/**
	 * Função que checa se o pacman pode continuar a se mover pro lado
	 * esquerdo.
	 * 
	 * @return
	 */
	private boolean canKeepGoingToTheLeftSide(){
		int row    = (int) Math.floor(y/h);
		int column = (int) Math.ceil(x/w) - 1;
		
		return isAnAvailableTile(row, column);
	}
	
	
	/**
	 * Função que checa se o pacman pode continuar a subir.
	 * 
	 * @return
	 */
	private boolean canKeepGoingUp(){
		int row    = (int) Math.ceil(y/h) - 1;
		int column = (int) Math.floor(x/w);
		
		return isAnAvailableTile(row, column);
	}
	
	
	/**
	 * Função que checa se o pacman pode ou não mudar em dado momento
	 * 
	 * @param direction
	 */
	private boolean canChangeDirection(KeyCode pressed_key) {
		if      (pressed_key == KeyCode.RIGHT) { return canKeepGoingToTheRightSide(); }
		else if (pressed_key == KeyCode.DOWN)  { return canKeepGoingDown();           }	
		else if (pressed_key == KeyCode.LEFT)  { return canKeepGoingToTheLeftSide();  }
		else if (pressed_key == KeyCode.UP)    { return canKeepGoingUp();             }

		return false;
	}
	
	
	/**
	 * Função para atualizar a direção do pacman
	 * 
	 * @param key_code
	 */
	private void updateDiretion(KeyCode pressed_key){
		direction = pressed_key;
		if      (pressed_key == KeyCode.RIGHT) { pacman.setOffSetY(0);  }
		else if (pressed_key == KeyCode.DOWN)  { pacman.setOffSetY(36); }	
		else if (pressed_key == KeyCode.LEFT)  { pacman.setOffSetY(72); }
		else if (pressed_key == KeyCode.UP)    { pacman.setOffSetY(108);}	
		
	}

	
	/**
	 * Função para atualizar a posição do pacman caso ele
	 * atinga os limites do game container.
	 * 
	 * @param gc
	 */
	private void checkBoundaries(GameContainer gc){
		float x_end = (float) (gc.getWidth() - w);	
		if (x < 0){
			setX(x_end);
		}else if (x > x_end) {
			setX(0);
		}
		
		float y_end = (float) (gc.getHeight() - w); 
		if (y < 0){
			setY(y_end);
		}else if (y > y_end) {
			setY(0);
		}
	}
	
	
	/**
	 * Função que movimenta o pacman.
	 * 
	 * @param displacement
	 */
	private void movingPacman(float displacement){
		if (direction == KeyCode.RIGHT) {
			if (canKeepGoingToTheRightSide()){
				setX(x + displacement);
				setY(y);
			}
		}
		else if (direction == KeyCode.DOWN) {
			if(canKeepGoingDown()){
				
				setX(x);
				setY(y + displacement);
				System.out.println(x);
				System.out.println(y);
			}
		} 
		else if (direction == KeyCode.LEFT) {
			if(canKeepGoingToTheLeftSide()){
				setX(x - displacement);
				setY(y);
			}
		}
		else if (direction == KeyCode.UP) {
			if(canKeepGoingUp()){
				setX(x); 
				setY(y - displacement);
			}
		}
		
	}

	
	@Override
	public void update(GameContainer gc, float dt) {
		Input game_input = gc.getInput();
		
		if (wasAKeyPressed(game_input)){
			KeyCode pressed_key = pressedKey(game_input);
			
			if(canChangeDirection(pressed_key)){
				updateDiretion(pressed_key);
				movingPacman(dt * speed);
			} 
			else {
				
			}
		}
		else{
			movingPacman(dt * speed);
		}
	
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
				manager.removeObject(object.getTag());
			} else if(object instanceof PurplePoint){
				manager.removeObject(object.getTag());
			}
		}
	}
}
