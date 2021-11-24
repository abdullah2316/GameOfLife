package com.mygroup.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Objects;

public class StatesController {
    @FXML
    public VBox main;
    public ScrollPane main_spane;
    public ImageView header;
    public Pane board;
    public VBox main_container;
    public Button back_btn;
    private char flag;

    @FXML
    public void initialize() {
        //setting header
        flag = Connector.data;
        System.out.println(flag);
        Image image_header = new Image(Objects.requireNonNull(this.getClass().getResource("images/header.png")).toString());
        header.setImage(image_header);
        //setting back button
        setIconToButton(back_btn, "images/back.png");
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
        for (int i = 0; i < 6; i++) {
            VBox individual_container = new VBox();
            individual_container.setMaxHeight(100);
            individual_container.setMinWidth(800);
            individual_container.setSpacing(5);
            individual_container.getStyleClass().add("Tetxtcontainer");

            HBox heading1 = new HBox();
            heading1.setAlignment(Pos.CENTER);
            heading1.setStyle("-fx-background-color:transparent");

            Label h1 = new Label();
            h1.getStyleClass().add("textlabelmain");
            h1.setText("State-1");
            heading1.getChildren().add(h1);

            individual_container.getChildren().add(heading1);

            HBox heading2 = new HBox();
            heading2.setMinWidth(800);
            heading2.setStyle("-fx-background-color:Transparent");

            VBox detailsContainer = new VBox();
            detailsContainer.setAlignment(Pos.CENTER_LEFT);
            detailsContainer.setMinWidth(400);

            Label h2 = new Label();
            h2.getStyleClass().add("textlabels");
            h2.setText("date Saved: 12-2-3");

            Label h3 = new Label();
            h3.getStyleClass().add("textlabels");
            h3.setText("Time Saved: 11:03");
            detailsContainer.getChildren().add(h2);
            detailsContainer.getChildren().add(h3);

            heading2.getChildren().add(detailsContainer);
            if (flag != 'v') {
                HBox buttonContainer = new HBox();
                buttonContainer.setAlignment(Pos.CENTER_RIGHT);
                buttonContainer.setMinWidth(400);

                Button button = new Button();
                button.getStyleClass().add("statebutton");
                if (flag == 'l') {
                    button.setText("LOAD");
                    setIconToButton(button, "images/cloud.png");
                } else if (flag == 'd') {
                    button.setText("Delete");
                    setIconToButton(button, "images/delete.png");
                }
                buttonContainer.getChildren().add(button);
                heading2.getChildren().add(buttonContainer);
            }
            individual_container.getChildren().add(heading2);

            Pane board = new Pane();
            board.setMaxHeight(200);
            board.setMinHeight(200);
            board.setStyle("-fx-background-color:Transparent");
            individual_container.getChildren().add(board);
            individual_container.setPadding(new Insets(0, 5, 10, 5));
            main_container.getChildren().add(individual_container);
            drawBoard(board);

        }
    }

    void drawBoard(Pane pane) {
        final double height = 200;//actual height of pane on screen
        final double width = 800;//actual width of pane on screen
        final int columsOnScreen = 30;//at zoom=1
        final double square_size = width / columsOnScreen;
        final int rowsOnScreen = 30;//at zoom=1
        int Total_columns = 30;//total columns
        int Total_rows = (int) (height / square_size);
        double Y_coordinate = 0;
        for (int row_index = 0; row_index < Total_rows; row_index = (int) Math.round(Y_coordinate / square_size)) {
            double X_coordinate = 0;
            for (int col_index = 0; col_index < Total_columns; col_index = (int) Math.round(X_coordinate / square_size)) {
                Rectangle r = new Rectangle(X_coordinate, Y_coordinate, square_size, square_size);
                r.setFill(Color.valueOf("#b0c4de"));
                r.setStroke(Color.valueOf("#1e90ff"));
                pane.getChildren().add(r);
                X_coordinate += square_size;
            }
            Y_coordinate += square_size;
        }
    }

    private void setIconToButton(Button button, String path) {
        Image image = new Image(Objects.requireNonNull(this.getClass().getResource(path)).toString());
        ImageView image_view = new ImageView(image);
        image_view.setFitHeight(25);
        image_view.setPreserveRatio(true);
        button.setGraphic(image_view);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        MainMenu obj = new MainMenu();
        Stage stageTheLayoutBelongs = (Stage) header.getScene().getWindow();
        Scene scene = stageTheLayoutBelongs.getScene();
        obj.change_scene(stageTheLayoutBelongs, scene, "MainMenu.fxml", true, "mainmenustyle.css");
    }
}
