package br.ufrpe.pixengine.components;

import java.util.ArrayList;
import java.util.Iterator;

import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;
import br.ufrpe.pixengine.pacman.BluePoint;
import br.ufrpe.pixengine.pacman.Ghost;

public class ObjectManager {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private int points_amount;
	private ArrayList<Ghost> ghosts = new ArrayList<Ghost>();

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
		if(object instanceof Ghost){
			ghosts.add((Ghost) object);
		}
		objects.add(object);
	}
	

	/**
	 * Funçãoo para remover um GameObject do ObjectManager 
	 * 
	 * @param tag
	 */
	public void removePoints(String tag) {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).getTag().equalsIgnoreCase(tag)) {
				objects.remove(i);
			}
		}
		points_amount--;
	}

	public GameObject findObject(String tag) {
		for (GameObject go : objects) {
			if (go.getTag().equalsIgnoreCase(tag)) {
				return go;
			}
		}

		return null;
	}

	public void setPoitsAmout(int amount) {
		points_amount = amount;
	}

	public int getPointsAmount() {
		return points_amount;
	}
	
	public void made_ghosts_weak () {
		for (Ghost ghostObject : ghosts) {
			ghostObject.get_weak();
		}
	}
}
