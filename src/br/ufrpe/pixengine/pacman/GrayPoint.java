package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.components.Collider;
import br.ufrpe.pixengine.components.GameObject;
import br.ufrpe.pixengine.components.ObjectManager;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;
import javafx.scene.image.Image;

public class GrayPoint extends GameObject{
	private Image point;

	public GrayPoint(float column, float row, String tag, ObjectManager manager) {
		setTag(tag);

		this.point = new Image("pacman/gray_point.png");

		this.x = 46 * column;
		this.y = 46 * row;
		
		this.w = 46;
		this.h = 46;
		
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
