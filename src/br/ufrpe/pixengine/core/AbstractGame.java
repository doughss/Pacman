package br.ufrpe.pixengine.core;

import java.util.Stack;

import br.ufrpe.pixengine.components.State;

public abstract class AbstractGame {
	private Stack<State> states = new Stack<State>();

	public abstract void init(GameContainer gc);

	public abstract void update(GameContainer gc, float dt);

	public abstract void render(GameContainer gc, Renderer r);

	public State peek() {
		return states.peek();
	}

	public void push(State s) {
		states.push(s);
	}

	public void pop() {
		states.pop();
	}

	public void setState(State s) {
		states.pop();
		states.push(s);
	}
}
