package br.ufrpe.pixengine.components;

import java.util.ArrayList;

import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;

public abstract class GameObject {
	protected float x, y, w, h;
	protected String tag = "null";
	protected boolean dead = false;
	private ArrayList<Component> components = new ArrayList<Component>();
	protected ObjectManager manager;

	public abstract void update(GameContainer gc, float dt);

	public abstract void render(GameContainer gc, Renderer r);

	public void updateComponents(GameContainer gc, float dt) {
		for (Component c : getComponents()) {
			c.update(gc, this, dt);
		}
	}

	public void renderComponents(GameContainer gc, Renderer r) {
		for (Component c : getComponents()) {
			c.render(gc, r);
		}
	}

	public abstract void componentEvent(String name, GameObject object);

	public abstract void dispose();

	public void addComponent(Component c) {
		getComponents().add(c);
	}

	public void removeComponent(String tag) {
		for (int i = 0; i < getComponents().size(); i++) {
			if (getComponents().get(i).getTag().equalsIgnoreCase(tag)) {
				getComponents().remove(i);
			}
		}
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public ArrayList<Component> getComponents() {
		return components;
	}

	public void setComponents(ArrayList<Component> components) {
		this.components = components;
	}
	
	public ObjectManager getManager() {
		return manager;
	}

}
