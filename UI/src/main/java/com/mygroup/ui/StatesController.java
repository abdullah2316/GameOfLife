package com.mygroup.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.Objects;

public class StatesController {
    @FXML
    public VBox main;
    public ScrollPane main_spane;
    public ImageView header;

    @FXML
    public void initialize() {
        //setting header
        Image image_header = new Image(Objects.requireNonNull(this.getClass().getResource("images/header.png")).toString());
        header.setImage(image_header);
        //setting background
        main.setMinHeight(705);
        main.setBackground(new Background(
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
//        //state and info container
//        SplitPane state_container = new SplitPane();
//        state_container.setMinWidth(300);
//        //state info portion
//        VBox text_container = new VBox();
//        Label text = new Label();
//        text.setText("State-1\nSaved on date: 12-2-3\nTime:11:03");
//        text_container.getChildren().add(text);
//        text_container.setMinHeight(70);
//        //state view
//        Pane Grid = new Pane();
//        //Grid.setMinWidth(500);
//        Grid.setStyle("-fx-background-color:red");
//        state_container.getItems().add(text_container);
//        state_container.getItems().add(Grid);
//
//        main_spane.setContent(state_container);
//        main_spane.setMinViewportWidth(400);
//        main_spane.setMinViewportHeight(70);


    }
}
