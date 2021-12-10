package com.mygroup.ui;

import BL.Data;
import BL.GameUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class MainMenu extends Application {
    private static Data datafuncs;
    private static GameUI gameFuncs;

    public MainMenu() {
    }

    public MainMenu(Data d, GameUI G) {
        datafuncs = d;
        gameFuncs = G;
    }

    public static Data get_BLD() {
        return datafuncs;
    }

    public static GameUI get_BL() {
        return gameFuncs;
    }

    public void init(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        Set_StyleSheet(scene, "mainmenustyle.css");
        stage.setTitle("GAME OF LIFE");
        stage.setScene(scene);
        //maximizing stage
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());

        stage.setMaxWidth(primaryScreenBounds.getWidth());
        stage.setMinWidth(primaryScreenBounds.getWidth());

        stage.setMaxHeight(primaryScreenBounds.getHeight());
        stage.setMinHeight(primaryScreenBounds.getHeight());
        //setting app icon
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("images/icon.png")).toString()));
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