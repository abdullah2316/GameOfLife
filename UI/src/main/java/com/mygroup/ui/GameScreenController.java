package com.mygroup.ui;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Objects;

public class GameScreenController {

    private final double height = 400;//actual height of pane on screen
    private final double width = 1340;//actual width of pane on screen
    private final int columsOnScreen = 40;//at zoom=1
    private final double square_size = width / columsOnScreen;
    private final int rowsOnScreen = 30;//at zoom=1
    public Pane header;
    public ImageView img;
    public Button back_btn;
    public VBox vb;
    public Pane grid_internal;
    public BorderPane grid_ext;
    public ScrollPane scroller;
    public Slider zoombar;
    public Button start;
    public HBox bottombox;
    public Button clear;
    public Button reset;
    public Button next;
    public HBox speedZoom;
    public StackPane trackPane;
    public Slider speedBar;
    public ImageView zoom_img;
    public ImageView speed_img;
    private int center_positionX;
    private int center_positionY;
    private int Total_columns = 40;//total columns
    private int Total_rows = 20;
    private Rectangle[][] Rectangles;

    public void initialize() {
        Rectangles = new Rectangle[Total_rows][Total_columns];
        Image image_header;
        //setting header image
        image_header = new Image(Objects.requireNonNull(this.getClass().getResource("images/header.png")).toString());
        img.setImage(image_header);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        System.out.println(primaryScreenBounds.getWidth());
        //setting up grid
        //logical width of pane i.e scrollable
        double logical_width = CalcLogicalwidth(Total_columns);
        //logical height of pane i.e scrollable
        double logical_height = CalcLogicalHeight(Total_rows);
        double Y_coordinate = 0;
        for (int row_index = 0; row_index < Total_rows; row_index = (int) Math.round(Y_coordinate / square_size)) {
            double X_coordinate = 0;
            for (int col_index = 0; col_index < Total_columns; col_index = (int) Math.round(X_coordinate / square_size)) {
                Rectangle r = new Rectangle(X_coordinate, Y_coordinate, square_size, square_size);
                r.setFill(Color.valueOf("#b0c4de"));
                r.setStroke(Color.valueOf("#1e90ff"));
                r.setOnMouseClicked(t -> r.setFill(Color.BLUE));
                Rectangles[row_index][col_index] = r;
                X_coordinate += square_size;
            }
            grid_internal.getChildren().addAll(Rectangles[row_index]);
            Y_coordinate += square_size;
        }
        //panning grid
        scroller.setPannable(true);
        scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.setPrefViewportHeight(400);
        //set scroller to the middle of the matrix
        scroller.setVvalue(500);
        scroller.setHvalue(500);
        //setting zoom functionality
        zoombar.setMax(2);
        zoombar.setMin(0.75);
        zoombar.setValue(1);
        grid_internal.scaleXProperty().bind(zoombar.valueProperty());
        grid_internal.scaleYProperty().bind(zoombar.valueProperty());
        //setting button icons
        setIconToButton(start, "images/play.png");
        setIconToButton(clear, "images/clear.png");
        setIconToButton(reset, "images/reset.png");
        setIconToButton(next, "images/next.png");
        setIconToButton(back_btn, "images/back.png");
        //setting slider icons
        setIcon(zoom_img, "images/zoom.png");
        setIcon(speed_img, "images/speed.png");
        //background
        vb.setMinHeight(705);
        vb.setBackground(new Background(
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

    public void back() throws Exception {
        MainMenu obj = new MainMenu();
        Stage stageTheLayoutBelongs = (Stage) header.getScene().getWindow();
        Scene scene = stageTheLayoutBelongs.getScene();
        obj.change_scene(stageTheLayoutBelongs, scene, "MainMenu.fxml", true, "mainmenustyle.css");

    }

    public void Reset_Grid() {
    }

    public double CalcLogicalwidth(int columns) {
        return square_size * columns;
    }

    public double CalcLogicalHeight(int rows) {
        return square_size * rows;
    }

    private void setIconToButton(Button button, String path) {
        Image image = new Image(Objects.requireNonNull(this.getClass().getResource(path)).toString());
        ImageView image_view = new ImageView(image);
        image_view.setFitHeight(25);
        image_view.setPreserveRatio(true);
        button.setGraphic(image_view);
    }

    private void setIcon(ImageView image_view, String path) {
        Image image = new Image(Objects.requireNonNull(this.getClass().getResource(path)).toString());
        image_view.setImage(image);
        image_view.setFitHeight(25);
        image_view.setPreserveRatio(true);
    }
}
