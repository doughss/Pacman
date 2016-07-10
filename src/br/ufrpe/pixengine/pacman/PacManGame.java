package br.ufrpe.pixengine.pacman;

import br.ufrpe.pixengine.core.GameContainer;
import javafx.application.Application;
import javafx.stage.Stage;

public class PacManGame extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        GameContainer gc = new GameContainer(new GameManager(), primaryStage);
        gc.setClearScreen(true);
        gc.setLightEnable(false);
        gc.setDynamicLights(false);
        gc.setLockFrameRate(true);
        gc.start();
        primaryStage.setOnCloseRequest(e -> exitPlatform(primaryStage));
    }
    
    private void exitPlatform(Stage primaryStage) {
        primaryStage.close();
        System.exit(0);
    }
  
    public static void main(String[] args) {
        launch(args);
    }

}
