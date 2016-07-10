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

public class PacMan extends GameObject{
	 private AnimatedImage pacman;
	 private KeyCode direction;
	 private int speed;

	public PacMan(float x, float y, String tag, ObjectManager manager) {
		setTag(tag);
		
		this.x = x;
		this.y = y;
		
		this.w = 46;
		this.h = 46;
		
		this.speed = 150; // mais pra frente deverá ser escolhido
		
		this.direction = KeyCode.RIGHT;
		this.pacman = new AnimatedImage("pacman/pacman_sprites.png", 400, 3, 3, 0, 0, 46, 46);
		
		this.manager = manager;
		
		addComponent(new Collider());
	}

	
	/**
	 * Função para atualizar a direção do pacman
	 * 
	 * @param game_input: Controlador de input do jogo
	 */
	private void updateDiretion(Input game_input){
		if (game_input.isKeyPressed(KeyCode.UP.ordinal())) {
			this.direction = KeyCode.UP;
			this.pacman.setOffSetY(138);
		}else if (game_input.isKeyPressed(KeyCode.LEFT.ordinal())) {
			this.direction = KeyCode.LEFT;
			this.pacman.setOffSetY(92);
		}else if (game_input.isKeyPressed(KeyCode.DOWN.ordinal())) {
			this.direction = KeyCode.DOWN;
			this.pacman.setOffSetY(46);
		}else if (game_input.isKeyPressed(KeyCode.RIGHT.ordinal())) {
			this.direction = KeyCode.RIGHT;
			this.pacman.setOffSetY(0);
		}
	}
	
	/**
	 * Função para atualizar a posição do pacman caso ele
	 * atinga os limites do game container.
	 * 
	 * @param gc
	 */
	private void checkBoundaries(GameContainer gc){
		float x_end = (float) (gc.getWidth() - this.w);	
		if (this.x < 0){
			this.setX(x_end);
		}else if (this.x > x_end) {
			this.setX(0);
		}
		
		float y_end = (float) (gc.getHeight() - this.w); 
		if (this.y < 0){
			this.setY(y_end);
		}else if (this.y > y_end) {
			this.setY(0);
		}
	}
	
	/**
	 * Função para atribuir a nova posição do pacman.
	 * 
	 * @param dt
	 */
	private void movingPacman(float displacement){
		if (this.direction == KeyCode.UP) {
			this.setX(this.x); 
			this.setY(this.y - displacement);
		}else if (this.direction == KeyCode.LEFT) {
			this.setX(this.x - displacement);
			this.setY(this.y);
		}else if (this.direction == KeyCode.DOWN) {
			this.setX(this.x);
			this.setY(this.y + displacement);
		}else if (this.direction == KeyCode.RIGHT) {
			this.setX(this.x + displacement);
			this.setY(this.y);
		}
	}

	
	@Override
	public void update(GameContainer gc, float dt) {
		Input game_input = gc.getInput();
		
		this.updateDiretion(game_input);
		this.movingPacman(dt*this.speed);
		this.checkBoundaries(gc);
		
		this.pacman.nextFrame(dt);
		updateComponents(gc, dt);
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		Rectangle2D pos = this.pacman.getCurrentFramePosition();
	    r.drawImage(this.pacman.getImage(), pos.getMinX(), pos.getMinY(), 
	    			pos.getWidth(), pos.getHeight(), x, y, 
	            	pos.getWidth(), pos.getHeight());
	}
	
	@Override
	public void dispose() {}
	@Override
	public void componentEvent(String name, GameObject object) {
		if (name.equalsIgnoreCase("collider")) {
			if(object instanceof GrayPoint){
				this.manager.removeObject(object.getTag());
			}
		}
	}
}
