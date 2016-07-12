package br.ufrpe.pixengine.core;

import br.ufrpe.pixengine.components.Physics;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameContainer implements Runnable {
	private Thread thread;
	private AbstractGame game;
	private Window window;
	private Renderer renderer;
	private Input input;
	private Physics physics;

	private int width = 720, height = 396;
	private float scale = 1.0f;
	private String title = "Oficina Maluca";
	private double frameCap = 1.5/60;
	private boolean isRunning = false;

	private boolean lockFrameRate = false;
	private boolean lightEnable = false;
	private boolean dynamicLights = false;
	private boolean clearScreen = false;
	private boolean debug = false;

	public GameContainer(AbstractGame game, Stage mainStage) {
		this.game = game;
		window = new Window(this, mainStage);
	}

	public void start() {
		if (isRunning)
			return;

		renderer = new Renderer(this);
		input = new Input(this);
		physics = new Physics();

		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		if (!isRunning)
			return;

		isRunning = false;
		
		this.window.getMainStage().close();
	}

	public void run() {
		isRunning = true;

		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		double totalFramesTime = 0;
		int frameCount = 0;
		int fps = 0;

		game.init(this);

		while (isRunning) {
			boolean render = !lockFrameRate;

			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;

			unprocessedTime += passedTime;
			totalFramesTime += passedTime;

			while (unprocessedTime >= frameCap) {
				if (input.isKeyPressed(KeyCode.F2.ordinal()))
					debug = !debug;

				game.update(this, (float) frameCap);
				physics.update();
				input.update();
				unprocessedTime -= frameCap;
				render = true;

				if (totalFramesTime >= 1.0) {
					// Tempo total maior que um segundo
					totalFramesTime = 0;
					fps = frameCount;
					frameCount = 0;
				}
			}

			if (render) {
				if (clearScreen)
					renderer.clear();

				game.render(this, renderer);
				
				renderer.setTranslate(false);
				if (debug)
					renderer.drawString("FPS-" + fps, Color.PALEGREEN, 0, 30, 14);
				renderer.setTranslate(true);

				window.update();
				frameCount++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		cleanUp();
	}

	private void cleanUp() {
		window.cleanUp();
	}

	public void setFrameCap(int number) {
		frameCap = 1.0 / number;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Window getWindow() {
		return window;
	}

	public boolean isDynamicLights() {
		return dynamicLights;
	}

	public void setDynamicLights(boolean dynamicLights) {
		this.dynamicLights = dynamicLights;
	}

	public boolean isLightEnable() {
		return lightEnable;
	}

	public void setLightEnable(boolean lightEnable) {
		this.lightEnable = lightEnable;
	}

	public boolean isClearScreen() {
		return clearScreen;
	}

	public void setClearScreen(boolean clearScreen) {
		this.clearScreen = clearScreen;
	}

	public AbstractGame getGame() {
		return game;
	}

	public void setGame(AbstractGame game) {
		this.game = game;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isLockFrameRate() {
		return lockFrameRate;
	}

	public void setLockFrameRate(boolean lockFrameRate) {
		this.lockFrameRate = lockFrameRate;
	}

	public Physics getPhysics() {
		return physics;
	}

	public void setPhysics(Physics physics) {
		this.physics = physics;
	}
}
