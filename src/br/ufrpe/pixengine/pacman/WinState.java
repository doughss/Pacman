package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.components.ObjectManager;
import br.ufrpe.pixengine.components.State;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Input;
import br.ufrpe.pixengine.core.Renderer;
import br.ufrpe.pixengine.core.fx.SoundClip;
import br.ufrpe.pixengine.pacman.PacMan;
import javafx.scene.input.KeyCode;


public class WinState extends State {
	SoundClip music_for_winner;

	public WinState() {
		manager.addObject(new GameImage("images/ending_screen.png"));
		music_for_winner = new SoundClip("pacman_win.wav");
		music_for_winner.play();
	}

	@Override
	public void update(GameContainer gc, float dt) {
		manager.updateObjects(gc, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		manager.renderObjects(gc, r);
	}

	@Override
	public void dipose() {
		manager.diposeObjects();
	}

	public ObjectManager getManager() {
		return manager;
	}

	public void setManager(ObjectManager manager) {
		this.manager = manager;
	}

}
