package br.ufrpe.pixengine.components;

import java.util.ArrayList;

import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;

public class ObjectManager {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();

	public void updateObjects(GameContainer gc, float dt) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update(gc, dt);

			if (objects.get(i).isDead())
				objects.remove(i);
		}
	}

	public void renderObjects(GameContainer gc, Renderer r) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render(gc, r);
		}
	}

	public void diposeObjects() {
		for (GameObject go : objects) {
			go.dispose();
		}
	}

	public void addObject(GameObject object) {
		objects.add(object);
	}
	

	/**
	 * Função para remover um GameObject do ObjectManager 
	 * 
	 * @param tag
	 */
	public void removeObject(String tag) {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).getTag().equalsIgnoreCase(tag)) {
				objects.remove(i);
			}
		}
	}

	public GameObject findObject(String tag) {
		for (GameObject go : objects) {
			if (go.getTag().equalsIgnoreCase(tag)) {
				return go;
			}
		}

		return null;
	}
}
