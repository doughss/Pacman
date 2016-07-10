package br.ufrpe.pixengine.components;

import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;

public abstract class State {
	protected ObjectManager manager = new ObjectManager();

	public abstract void update(GameContainer gc, float dt);

	public abstract void render(GameContainer gc, Renderer r);

	public abstract void dipose();

	public ObjectManager getManager() {
		return manager;
	}

	public void setManager(ObjectManager manager) {
		this.manager = manager;
	}
}
