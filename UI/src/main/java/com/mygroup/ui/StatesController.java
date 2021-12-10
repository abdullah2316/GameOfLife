package com.mygroup.ui;

import javafx.event.EventHandler;
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

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Objects;

import static com.mygroup.ui.Connector.currentAlive;

public class StatesController {
    @FXML
    public VBox main;
    public ScrollPane main_spane;
    public ImageView header;
    public Pane board;
    public VBox main_container;
    public Button back_btn;

    @FXML
    public void initialize() {
        //setting header
        char flag = Connector.flag1;
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
        int count = 1;
        for (int i = 0; i < currentAlive.get(0); i++) {
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
            h1.setText(String.valueOf(Connector.state_info.get(i * 5)));
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
            h2.setText("date Saved: " + Connector.state_info.get((i * 5) + 1));

            Label h3 = new Label();
            h3.getStyleClass().add("textlabels");
            h3.setText("Time Saved: " + Connector.state_info.get((i * 5) + 2));
            Label h4 = new Label();
            h4.getStyleClass().add("textlabels");
            h4.setText("Generation: " + Connector.state_info.get((i * 5) + 3));
            detailsContainer.getChildren().add(h2);
            detailsContainer.getChildren().add(h3);
            detailsContainer.getChildren().add(h4);

            heading2.getChildren().add(detailsContainer);
            if (flag != 'v') {
                HBox buttonContainer = new HBox();
                buttonContainer.setAlignment(Pos.CENTER_RIGHT);
                buttonContainer.setMinWidth(400);

                Button button = new Button();
                button.getStyleClass().add("statebutton");
                button.setId(String.valueOf(Connector.state_info.get((i * 5) + 4)));
                // button.setId(String.valueOf(count));
                if (flag == 'l') {
                    button.setText("LOAD");
                    setIconToButton(button, "images/cloud.png");
                    button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            try {
                                MainMenu.get_BL().Load_A_State(button.getId());
                            } catch (SQLException | ClassNotFoundException | FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            MainMenu obj = new MainMenu();
                            Stage stageTheLayoutBelongs = (Stage) header.getScene().getWindow();
                            Scene scene = stageTheLayoutBelongs.getScene();
                            try {
                                obj.change_scene(stageTheLayoutBelongs, scene, "Gamescreen.fxml", true, "GameScreen.css");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else if (flag == 'd') {
                    button.setText("Delete");
                    setIconToButton(button, "images/delete.png");
                    button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            individual_container.getChildren().clear();
                            individual_container.setVisible(false);
                            individual_container.setManaged(false);
                            main.getChildren().remove(individual_container);
                            String id = button.getId();
                            try {
                                MainMenu.get_BLD().deletestate(id);
                            } catch (SQLException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    });
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
            count = drawBoard(board, count);

        }
    }


    int drawBoard(Pane pane, int count) {
        final double height = 200;//actual height of pane on screen
        final double width = 800;//actual width of pane on screen
        final int columsOnScreen = 60;//at zoom=1
        final double square_size = width / columsOnScreen;
        final int rowsOnScreen = 30;//at zoom=1
        int Total_columns = 60;//total columns
        int Total_rows = (int) (height / square_size);
        Rectangle[][] Rectangles = new Rectangle[Total_rows][Total_columns];
        double Y_coordinate = 0;
        for (int row_index = 0; row_index < Total_rows; row_index = (int) Math.round(Y_coordinate / square_size)) {
            double X_coordinate = 0;
            for (int col_index = 0; col_index < Total_columns; col_index = (int) Math.round(X_coordinate / square_size)) {
                Rectangle r = new Rectangle(X_coordinate, Y_coordinate, square_size, square_size);

                r.setFill(Color.valueOf("#b0c4de"));
                r.setStroke(Color.valueOf("#1e90ff"));
                pane.getChildren().add(r);
                Rectangles[row_index][col_index] = r;
                X_coordinate += square_size;
            }
            Y_coordinate += square_size;
        }
        int i;

        for (i = count + 1; i < (currentAlive.get(count) * 2) + (count + 1); i += 2) {
            System.out.println(currentAlive.get(i) + " " + currentAlive.get(i + 1));
            if (currentAlive.get(i) <= 27 && currentAlive.get(i + 1) >= 13)
                Rectangles[currentAlive.get(i) - 13][currentAlive.get(i + 1) - 20].setFill(Color.BLUE);
        }
        return i;
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
