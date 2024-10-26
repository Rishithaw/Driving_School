package com.ijse.gdse.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/view/HomePageView.fxml"));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("BetterDrive");
        //stage.setFullScreen(true);
        stage.show();
    }
}
