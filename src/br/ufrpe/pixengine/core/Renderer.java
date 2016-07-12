package br.ufrpe.pixengine.core;

import br.ufrpe.pixengine.core.fx.Pixel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Renderer {
	private GraphicsContext graphics;
	private int width, height;
	private int clearColor = 0xff000000;
	
	private int transX, transY;
	private boolean translate = true;

	public Renderer(GameContainer gc) {
		width = gc.getWidth();
		height = gc.getHeight();
		
		this.graphics = gc.getWindow().getCanvas().getGraphicsContext2D();
	}

	public void drawString(String text, int color, int offX, int offY, int size) {
	    this.setCurrentColor(color);
	    this.graphics.setFont(Font.font("Courrier", size));
	    this.graphics.fillText(text, offX + transX, offY + transY);
	}
	
	public void drawString(String text, Color color, int offX, int offY, int size) {
	    this.graphics.setFill(color);
        this.graphics.setFont(Font.font("Courrier", size));
        this.graphics.fillText(text, offX + transX, offY + transY);
    }
	
	private void setCurrentColor(int color) {
	    double a = Pixel.getAlpha(color);
        double r = Pixel.getRed(color);
        double g = Pixel.getGreen(color);
        double b = Pixel.getBlue(color);
        this.graphics.setFill(Color.color(r, g, b, a));
	}

	public void clear() {
	    this.setCurrentColor(clearColor);
	    this.graphics.fillRect(0, 0, this.width, this.height);
    }

	public void drawImage(Image image, double offX, double offY) {
	    this.graphics.drawImage(image, offX + transX, offY + transY);
	}
	
	public void drawImage(Image img, double sx, double sy, double sw, double sh, 
	        double dx, double dy, double dw, double dh) {
        this.graphics.drawImage(img, sx, sy, sw, sh, dx, dy, dw, dh);
    }

    public void drawRect(int offX, int offY, int w, int h, Color color, double lineWidth) {
	    this.graphics.setLineWidth(lineWidth);
	    this.graphics.setStroke(color);
	    this.graphics.setFill(Color.TRANSPARENT);
	    this.graphics.strokeRect(offX + transX, offY + transY, w, h);
	}

	public void drawFillRect(int offX, int offY, int w, int h, int color) {
	    this.setCurrentColor(color);
        this.graphics.fillRect(offX + transX, offY + transY, w, h);
	}
	
	public void drawFillRect(int offX, int offY, int w, int h, Color color) {
        this.graphics.setFill(color);
        this.graphics.fillRect(offX + transX, offY + transY, w, h);
    }	

	public int getClearColor() {
		return clearColor;
	}

	public void setClearColor(int clearColor) {
		this.clearColor = clearColor;
	}

	public Font getFont() {
		return this.graphics.getFont();
	}

	public void setFont(Font font) {
	    this.graphics.setFont(font);;
	}

	public int getTransX() {
		return transX;
	}

	public void setTransX(int transX) {
		this.transX = transX;
	}

	public int getTransY() {
		return transY;
	}

	public void setTransY(int transY) {
		this.transY = transY;
	}

	public void drawImage(Image image) {
		this.drawImage(image, 0, 0);
	}

	public boolean isTranslate() {
		return translate;
	}

	public void setTranslate(boolean translate) {
		this.translate = translate;
	}
}
