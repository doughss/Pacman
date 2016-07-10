package br.ufrpe.pixengine.core.fx;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class AnimatedImage {
    
    private double totalDuration;
    private Image image;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final int frameWidth;
    private final int frameHeight;
    
    private int currentIndex;
    private long passedTime;
    private long startTime;
    
    public AnimatedImage (
            String imageName,
            int totalDurationInMilis, 
            int count,   int columns,
            int offsetX, int offsetY,
            int frameWidth,   int frameHeight) {
        this.image = new Image(imageName);        
        this.totalDuration = totalDurationInMilis;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.frameWidth     = frameWidth;
        this.frameHeight    = frameHeight;
        this.startTime = System.currentTimeMillis();
    }
    
    public Rectangle2D getCurrentFramePosition() {
        int x = (this.currentIndex % columns) * frameWidth  + offsetX;
        int y = (this.currentIndex / columns) * frameHeight + offsetY;
        Rectangle2D position = new Rectangle2D(x, y, frameWidth, frameHeight);
        return position;
    }
    
    public void nextFrame(double deltaTime) {
        double frameDuration = this.totalDuration / count;
        this.currentIndex = (int) (this.passedTime / frameDuration);
        this.passedTime = System.currentTimeMillis() - this.startTime;
        if (this.passedTime >= this.totalDuration) {
            this.passedTime = 0;
            this.startTime = System.currentTimeMillis();
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public void setOffSetY(int offsetY) {
        this.offsetY = offsetY;
    }

}