package com.mygroup.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Objects;

public class MainMenuController {
    @FXML
    private Button new_state;
    @FXML
    private Button load_state;
    @FXML
    private Button exit;
    @FXML
    private VBox layout;
    @FXML
    private ImageView img;
    @FXML
    private Button View_States;
    @FXML
    private Button Delete_state;
    @FXML
    private VBox menu;

    @FXML
    public void initialize() {
        Connector temp = new Connector();
        //setting header image
        Image image_header;
        image_header = new Image(Objects.requireNonNull(this.getClass().getResource("images/header.png")).toString());
        img.setImage(image_header);
        //setting button icons
        setIcon(load_state, "images/cloud.png");
        setIcon(new_state, "images/plus.png");
        setIcon(View_States, "images/eye.png");
        setIcon(Delete_state, "images/delete.png");
        setIcon(exit, "images/exit.png");
        //setting up background image
        menu.setMinHeight(705);
        menu.setBackground(new Background(
                Collections.singletonList(new BackgroundFill(
                        Color.WHITE,
                        new CornerRadii(0),
                        new Insets(0))),
                Collections.singletonList(new BackgroundImage(
                        new Image(Objects.requireNonNull(this.getClass().getResource("images/mainmenubackground.jpg")).toString(), 100, 100, false, true),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(1.0, 1.0, true, true, false, false)
                ))));

    }

    @FXML
    private void switch_to_gamescreen() throws Exception {
        MainMenu obj = new MainMenu();
        Stage stageTheLayoutBelongs = (Stage) layout.getScene().getWindow();
        Scene scene = stageTheLayoutBelongs.getScene();
        obj.change_scene(stageTheLayoutBelongs, scene, "Gamescreen.fxml", true, "GameScreen.css");
    }

    @FXML
    private void switch_to_savedStates(MouseEvent e) throws Exception {
        if (Objects.equals(((Control) e.getSource()).getId(), "View_States"))
            Connector.flag1 = 'v';
        else if (Objects.equals(((Control) e.getSource()).getId(), "load_state"))
            Connector.flag1 = 'l';
        else
            Connector.flag1 = 'd';
        Connector temp = new Connector();

        MainMenu.get_BLD().Load(Connector.state_info, Connector.currentAlive);
        System.out.println(((Control) e.getSource()).getId());
        MainMenu obj = new MainMenu();
        Stage stageTheLayoutBelongs = (Stage) layout.getScene().getWindow();
        Scene scene = stageTheLayoutBelongs.getScene();
        obj.change_scene(stageTheLayoutBelongs, scene, "States.fxml", true, "States.css");
    }

    private void setIcon(Button button, String path) {
        Image image = new Image(Objects.requireNonNull(this.getClass().getResource(path)).toString());
        ImageView image_view = new ImageView(image);
        image_view.setFitHeight(25);
        image_view.setPreserveRatio(true);
        button.setGraphic(image_view);
    }
}