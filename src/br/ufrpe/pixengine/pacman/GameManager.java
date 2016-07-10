package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.core.AbstractGame;
import br.ufrpe.pixengine.core.GameContainer;
import br.ufrpe.pixengine.core.Renderer;

public class GameManager extends AbstractGame {
    
	public GameManager() {
		push(new FirstStage());
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		peek().update(gc, dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		peek().render(gc, r);
	}

	@Override
	public void init(GameContainer gc) {
//		peek().getManager()
	}

}
