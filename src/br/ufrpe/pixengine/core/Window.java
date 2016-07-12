package br.ufrpe.pixengine.core;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Window {
	private Stage mainStage;
	private Scene mainScene;
	private Canvas canvas;

	public Window(GameContainer gc, Stage primaryStage) {
	    
		mainStage = primaryStage;
        mainStage.setTitle(gc.getTitle());

        Group root = new Group();
	    mainScene = new Scene(root);
	 
	    canvas = new Canvas(gc.getWidth() * gc.getScale(), gc.getHeight() * gc.getScale());
	    root.getChildren().add(canvas);
		
		mainStage.setScene(mainScene);
		
		mainStage.setResizable(false);
		mainStage.show();
	}

	public void update() {
	    // Implementar se for necessário alguma atualização da janela durante
	    // a execução do Jogo
	}
	
	public void cleanUp() {
		mainStage.close();
	}

    public Canvas getCanvas() {
        return canvas;
    }

    public Scene getScene() {
        return this.mainScene;
    }
    
    public Stage getMainStage() {
        return mainStage;
    }
}
