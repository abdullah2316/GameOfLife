package com.mygroup.ui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainMenuController {
    @FXML
    public Label title;
    public Button new_state;
    public Button load_state;
    public Button rules;
    public Button exit;
    @FXML
    public void switch_to_SavedStates() throws Exception {
        MainMenu obj = new MainMenu();
        Stage stageTheLabelBelongs = (Stage) title.getScene().getWindow();
        Scene scene = stageTheLabelBelongs.getScene();
        obj.change_scene(stageTheLabelBelongs, scene, "SavedStates.fxml", true, "SavedStates.css");
    }
    //protected void onHelloButtonClick() {
      //  welcomeText.setText("Welcome to JavaFX Application!");
    //}
}