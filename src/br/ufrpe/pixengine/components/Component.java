package br.ufrpe.pixengine.components;

import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;

public abstract class Component {
	protected String tag = null;
	public abstract void update(GameContainer gc, GameObject object, float dt);
	public abstract void render(GameContainer gc, Renderer r);

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
