package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.components.Collider;
import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;
import javafx.scene.image.Image;

public class Wall extends GameObject{
	private Image point;

	public Wall(int column, int row, String tag) {
		setTag(tag);

		this.point = new Image("pacman/images/wall.png");
		this.w = 36;
		this.h = 36;
		
		this.x = this.w * column;
		this.y = this.h * row;
		
		addComponent(new Collider());
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
