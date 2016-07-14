package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.components.Collider;
import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.components.ObjectManager;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;
import javafx.scene.image.Image;

public class BluePoint extends GameObject{
	private Image point;

	public BluePoint(float column, float row, String tag, ObjectManager manager) {
		setTag(tag);

		this.point = new Image("images/blue_point.png");

		this.x = 36 * column + 13;
		this.y = 36 * row + 13;
		
		this.w = 10;
		this.h = 10;
		
		this.manager = manager;

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
