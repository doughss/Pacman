package br.ufrpe.pixengine.core.fx;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private Image image;
    private final ImageView imageView;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int frameWidth;
    private final int frameHeight;

    private int lastIndex;

    public SpriteAnimation(
            String imageName,
            Duration duration, 
            int count,   int columns,
            int offsetX, int offsetY,
            int frameWidth,   int frameHeight) {
        this.image = new Image(imageName);
        this.imageView = new ImageView(this.image);
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, frameWidth, frameHeight));
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.frameWidth     = frameWidth;
        this.frameHeight    = frameHeight;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }
    
    public SpriteAnimation(
            ImageView imageView, 
            Duration duration, 
            int count,   int columns,
            int offsetX, int offsetY,
            int width,   int height) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.frameWidth     = width;
        this.frameHeight    = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * frameWidth  + offsetX;
            final int y = (index / columns) * frameHeight + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, frameWidth, frameHeight));
            lastIndex = index;
        }
    }

    public ImageView getImageView() {
        return this.imageView;
    }
    
    public void setSpriteX(double x) {
        this.imageView.setX(x);
    }
    
    public void setSpriteY(double y) {
        this.imageView.setY(y);
    }
    
    public double getSpriteX() {
        return this.imageView.getX();
    }
    
    public double getSpriteY() {
        return this.imageView.getY();
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
    
    
}
