package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;
import javafx.scene.image.Image;

public class Wall extends GameObject{
	private Image point;

	public Wall(int column, int row) {
		setTag("grayPoint");

		this.point = new Image("pacman/wall.png");
		this.w = 46;
		this.h = 46;
		
		this.x = this.w * column;
		this.y = this.h * row;
	}

	@Override
	public void update(GameContainer gc, float dt) {
		updateComponents(gc, dt);
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(this.point, this.x, this.y);
	}
	
	@Override
	public void dispose() {}
	@Override
	public void componentEvent(String name, GameObject object) {}
}
