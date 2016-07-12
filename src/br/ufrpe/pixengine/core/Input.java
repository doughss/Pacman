package br.ufrpe.pixengine.core;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Input implements EventHandler<InputEvent> {
	private GameContainer gc;

	private boolean[] keys = new boolean[256];
	private boolean[] keysLast = new boolean[256];

	private boolean[] buttons = new boolean[5];
	private boolean[] buttonsLast = new boolean[5];

	private int mouseX, mouseY;

	public Input(GameContainer gc) {
		this.gc = gc;
		
		// Key events
		gc.getWindow().getScene().setOnKeyPressed(this);
		gc.getWindow().getScene().setOnKeyReleased(this);
		gc.getWindow().getScene().setOnKeyTyped(this);
		
		// Mouse events
		gc.getWindow().getScene().setOnMousePressed(this);
		gc.getWindow().getScene().setOnMouseReleased(this);
		gc.getWindow().getScene().setOnMouseClicked(this);
		gc.getWindow().getScene().setOnMouseDragged(this);
		gc.getWindow().getScene().setOnMouseEntered(this);
		gc.getWindow().getScene().setOnMouseExited(this);
		gc.getWindow().getScene().setOnMouseMoved(this);
	}

	public void update() {
		keysLast = keys.clone();
		buttonsLast = buttons.clone();
	}

	public boolean isKey(int keyCode) {
		return keys[keyCode];
	}

	public boolean isKeyPressed(int keyCode) {
		return keys[keyCode] && !keysLast[keyCode];
	}

	public boolean isKeyReleased(int keyCode) {
		return !keys[keyCode] && keysLast[keyCode];
	}

	public boolean isButton(int button) {
		return buttons[button];
	}

	public boolean isButtonPressed(int button) {
		return buttons[button] && !buttonsLast[button];
	}

	public boolean isButtonReleased(int button) {
		return !buttons[button] && buttonsLast[button];
	}

	public void mouseClicked(MouseEvent e) {
	    // Not implemented yet
	}

	public void mouseEntered(MouseEvent e) {
	    // Not implemented yet
	}

	public void mouseExited(MouseEvent e) {
	    // Not implemented yet
	}

	public void mousePressed(MouseEvent e) {
		buttons[e.getButton().ordinal()] = true;
	}

	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton().ordinal()] = false;
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

    @Override
    public void handle(InputEvent event) {
        EventType<? extends InputEvent> eventType = event.getEventType();
        if (eventType.equals(KeyEvent.KEY_PRESSED)) {
            keys[((KeyEvent) event).getCode().ordinal()] = true;
        } else if (eventType.equals(KeyEvent.KEY_RELEASED)) {
            keys[((KeyEvent) event).getCode().ordinal()] = false;
        } else if (eventType.equals(KeyEvent.KEY_TYPED)) {
            // Not implemented yet
        } else if (eventType.equals(MouseEvent.MOUSE_DRAGGED) 
                || eventType.equals(MouseEvent.MOUSE_MOVED)) {
            MouseEvent e = (MouseEvent) event;
            mouseX = (int) (e.getX() / gc.getScale());
            mouseY = (int) (e.getY() / gc.getScale());
        } else if (eventType.equals(MouseEvent.MOUSE_ENTERED)) {
            // Not implemented yet            
        } else if (eventType.equals(MouseEvent.MOUSE_EXITED)) {
            // Not implemented yet            
        } else if (eventType.equals(MouseEvent.MOUSE_CLICKED)) {
            // Not implemented yet            
        } else if (eventType.equals(MouseEvent.MOUSE_PRESSED)) {
            buttons[((MouseEvent) event).getButton().ordinal()] = true;
        } else if (eventType.equals(MouseEvent.MOUSE_RELEASED)) {
            buttons[((MouseEvent) event).getButton().ordinal()] = false;
        } 
    }

}
