package com.mygroup.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Objects;

public class MainMenuController {
    @FXML
    public Label title;
    public Button new_state;
    public Button load_state;
    public Button exit;
    public VBox layout;
    public Pane header;
    public Label temp;
    public ImageView img;
    public Button View_States;
    public Button Delete_state;
    public VBox menu;

    @FXML
    public void initialize()
    {
        Image image_header,image_loadstate,image_newstate,image_viewstate,image_deletestate,image_exit;
        //setting header image
        image_header=new Image(Objects.requireNonNull(this.getClass().getResource("header.png")).toString());
        img.setImage(image_header);
        //setting button icons
        //load
        image_loadstate=new Image(Objects.requireNonNull(this.getClass().getResource("cloud.png")).toString());
      ImageView image_load_state_view=new ImageView(image_loadstate);
        image_load_state_view.setFitHeight(25);
        image_load_state_view.setPreserveRatio(true);
      load_state.setGraphic(image_load_state_view);
      //new state
        image_newstate=new Image(Objects.requireNonNull(this.getClass().getResource("plus.png")).toString());
        ImageView image_new_state_view=new ImageView(image_newstate);
        image_new_state_view.setFitHeight(25);
        image_new_state_view.setPreserveRatio(true);
        new_state.setGraphic(image_new_state_view);
        //view state
        image_viewstate=new Image(Objects.requireNonNull(this.getClass().getResource("eye.png")).toString());
        ImageView image_view_state_view=new ImageView(image_viewstate);
        image_view_state_view.setFitHeight(25);
        image_view_state_view.setPreserveRatio(true);
        View_States.setGraphic(image_view_state_view);
        //delete state
        image_deletestate=new Image(Objects.requireNonNull(this.getClass().getResource("delete.png")).toString());
        ImageView image_delete_state_view=new ImageView(image_deletestate);
        image_delete_state_view.setFitHeight(25);
        image_delete_state_view.setPreserveRatio(true);
        Delete_state.setGraphic(image_delete_state_view);
        //EXIT
        image_exit=new Image(Objects.requireNonNull(this.getClass().getResource("exit.png")).toString());
        ImageView image_exit_view=new ImageView(image_exit);
        image_exit_view.setFitHeight(25);
        image_exit_view.setPreserveRatio(true);
        exit.setGraphic(image_exit_view);
        //setting up background image
//        Image background=new Image(Objects.requireNonNull(this.getClass().getResource("mainmenubackground.jpg")).toString());
//        BackgroundImage myBI= new BackgroundImage(background,
//        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//        BackgroundSize.DEFAULT);
        menu.setMinHeight(625);
        menu.setBackground( new Background(
                Collections.singletonList(new BackgroundFill(
                        Color.WHITE,
                        new CornerRadii(0),
                        new Insets(0))),
                Collections.singletonList(new BackgroundImage(
                        new Image(Objects.requireNonNull(this.getClass().getResource("mainmenubackground.jpg")).toString(), 100, 100, false, true),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(1.0, 1.0, true, true, false, false)
                ))));

    }
    @FXML
    public void switch_to_SavedStates() throws Exception {
        MainMenu obj = new MainMenu();
        Stage stageTheLayoutBelongs = (Stage) layout.getScene().getWindow();
        Scene scene = stageTheLayoutBelongs.getScene();
        obj.change_scene(stageTheLayoutBelongs, scene, "SavedStates.fxml", true, "SavedStates.css");
    }
    //protected void onHelloButtonClick() {
      //  welcomeText.setText("Welcome to JavaFX Application!");
    //}
}