package com.mygroup.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("MainMenu.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Game of Life");
//        stage.setScene(scene);
//        stage.show();
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        Set_StyleSheet(scene, "mainmenustyle.css");
        stage.setTitle("GAME OF LIFE");
        stage.setScene(scene);
      stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }
    public void Set_StyleSheet(Scene scene, String StyleSheet) {
        scene.getStylesheets().removeAll();
        URL url = this.getClass().getResource(StyleSheet);
        if (url == null) {
            System.out.println("Resource not found!");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);

    }
    public void change_scene(Stage stage, Scene scene, String sceneName, boolean change_StyleSheet, String StyleSheet) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource(sceneName));
        scene.setRoot(fxmlLoader.load());
        if (change_StyleSheet) {
            Set_StyleSheet(scene, StyleSheet);
        }
        stage.setScene(scene);
    }

}