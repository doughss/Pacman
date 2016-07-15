package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.components.ObjectManager;
import br.ufrpe.pixengine.components.State;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Input;
import br.ufrpe.pixengine.core.Renderer;
import br.ufrpe.pixengine.core.fx.SoundClip;
import br.ufrpe.pixengine.pacman.PacMan;
import javafx.scene.input.KeyCode;


public class GameOverState extends State {
	SoundClip gameover_sound;

	public GameOverState() {
		manager.addObject(new GameImage("images/game_over_screen.png"));
		gameover_sound = new SoundClip("pacman_gameover.wav");
		gameover_sound.play();
	}

	@Override
	public void update(GameContainer gc, float dt) {
		Input game_input = gc.getInput();
		if (game_input.isKeyPressed(KeyCode.ENTER.ordinal())) {
			gameover_sound.stop();
			gc.getGame().pop();
			gc.getGame().push(new FirstState());
		} 
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
