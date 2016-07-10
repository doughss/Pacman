package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;
import javafx.scene.image.Image;


public class GameImage extends GameObject{
	private Image background_image;
	
	
	public GameImage(String background_path, float x, float y) {
		this.background_image = new Image(background_path);
		this.x = x;
		this.y = y;
	}
	
	public GameImage(String background_path) {
		this.background_image = new Image(background_path);
	}

	@Override
	public void update(GameContainer gc, float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(this.background_image, this.x, this.y);
	}

	@Override
	public void componentEvent(String name, GameObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

}
