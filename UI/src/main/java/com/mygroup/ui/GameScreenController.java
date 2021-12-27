package com.mygroup.ui;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

public class GameScreenController {

    private final double width = 1340;//actual width of pane on screen
    private final int columsOnScreen = 60;//at zoom=1
    private final double square_size = width / columsOnScreen;
    public Label generation;
    public Label gen;

    int total_rows = 40;
    //total columns
    int total_columns = 100;
    @FXML
    private Button save;
    private Timeline timer;
    @FXML
    private Button clear;
    @FXML
    private Button reset;
    @FXML
    private Button next;
    @FXML
    private Pane grid_internal;
    @FXML
    private ScrollPane scroller;
    @FXML
    private Slider zoombar;
    @FXML
    private Button start;
    @FXML
    private Pane header;
    @FXML
    private ImageView img;
    @FXML
    private Button back_btn;
    @FXML
    private VBox vb;
    @FXML
    private Slider speedBar;
    @FXML
    private ImageView zoom_img;
    @FXML
    private ImageView speed_img;

    private Rectangle[][] Rectangles;

    public void initialize() {

        Rectangles = new Rectangle[total_rows][total_columns];
        Image image_header;
        //setting header image
        image_header = new Image(Objects.requireNonNull(this.getClass().getResource("images/header.png")).toString());
        img.setImage(image_header);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //setting up grid
        //logical width of pane i.e scrollable
        //logical height of pane i.e scrollable
        double Y_coordinate = 0;
        for (int row_index = 0; row_index < total_rows; row_index = (int) Math.round(Y_coordinate / square_size)) {
            double X_coordinate = 0;
            for (int col_index = 0; col_index < total_columns; col_index = (int) Math.round(X_coordinate / square_size)) {
                Rectangle r = new Rectangle(X_coordinate, Y_coordinate, square_size, square_size);
                r.setFill(Color.valueOf("#b0c4de"));
                r.setStroke(Color.valueOf("#1e90ff"));
                r.setOnMouseClicked(t -> {
                    if (r.getFill() != Color.BLUE)
                        r.setFill(Color.BLUE);
                    else
                        r.setFill(Color.valueOf("#b0c4de"));
                    String row = r.getId().substring(0, (r.getId().indexOf('+')));
                    String col = r.getId().substring((r.getId().indexOf('+') + 1));
                    Helper.write_argument(Integer.parseInt(row), "input1");
                    Helper.write_argument(Integer.parseInt(col), "input2");
                    MainMenu.get_BL().isClicked();
                });
                r.setId(row_index + "+" + col_index);
                Rectangles[row_index][col_index] = r;
                X_coordinate += square_size;
            }
            grid_internal.getChildren().addAll(Rectangles[row_index]);
            Y_coordinate += square_size;
        }
        //set alive cells

        //panning grid
        scroller.setPannable(true);
        scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.setPrefViewportHeight(400);
        //set scroller to the middle of the matrix
        System.out.println(get_centralCol(60) + " , " + get_centralRow(15));
        scroller.setVvalue(0.5);
        scroller.setHvalue(0.5);
        //setting zoom functionality
        zoombar.setMax(2);
        zoombar.setMin(1);
        zoombar.setValue(1);
        grid_internal.scaleXProperty().bind(zoombar.valueProperty());
        grid_internal.scaleYProperty().bind(zoombar.valueProperty());
        //setting speed functionality
        speedBar.setMin(0.5);
        speedBar.setValue(1);
        speedBar.setMax(1.5);

        //setting button icons
        setIconToButton(start, "images/play.png");
        setIconToButton(clear, "images/clear.png");
        setIconToButton(reset, "images/reset.png");
        setIconToButton(next, "images/next.png");
        setIconToButton(back_btn, "images/back.png");
        setIconToButton(save, "images/save.png");
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
        // setting update time
        timer = set_timer(speedBar);
        if (Helper.flag1 != 'l') {
            Helper.write_argument(40, "input1");
            Helper.write_argument(100, "input2");
            MainMenu.get_BL().ConstructBoard();
            fill_cells(Helper.O_to_Int());
        } else {
            MainMenu.get_BL().start();
            fill_cells(Helper.O_to_Int());
        }

    }

    private double get_centralRow(int requiredRowFrame) {
        int r = (total_rows - requiredRowFrame) / 2;
        System.out.println("row: " + r);
        return (r * square_size);
    }

    private double get_centralCol(int requiredColFrame) {
        int c = (total_columns - requiredColFrame) / 4;
        System.out.println("col: " + c);
        return (c * square_size);
    }

    private Timeline set_timer(Slider slider) {

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            MainMenu.get_BL().updateBoard();
            ArrayList<Integer> cells = Helper.O_to_Int();
            update_cells(cells);
            int count = Integer.parseInt(generation.getText());
            count++;
            generation.setText(String.valueOf(count));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        // binding timer to speed bar
        timeline.rateProperty()
                .bind(new SimpleDoubleProperty(1.0)
                        .divide(slider.valueProperty()));

        return timeline;
    }

    @FXML
    private void back() throws Exception {
        timer.stop();
        reset();
        MainMenu obj = new MainMenu();
        Stage stageTheLayoutBelongs = (Stage) header.getScene().getWindow();
        Scene scene = stageTheLayoutBelongs.getScene();
        obj.change_scene(stageTheLayoutBelongs, scene, "MainMenu.fxml", true, "mainmenustyle.css");
    }

    private void update_cells(ArrayList<Integer> cells) {
        for (int i = 0; i < cells.size(); i += 2) {
            if (cells.get(i) < 0) {
                Rectangles[(cells.get(i) * -1)][(cells.get(i + 1) * -1)].setFill(Color.valueOf("#b0c4de"));
            } else
                Rectangles[cells.get(i)][cells.get(i + 1)].setFill(Color.BLUE);
        }

    }

    private void fill_cells(ArrayList<Integer> cells) {
        for (int i = 0; i < cells.size(); i += 2) {
            Rectangles[cells.get(i)][cells.get(i + 1)].setFill(Color.BLUE);
        }

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

    public void start(MouseEvent mouseEvent) {
        Button b = (Button) mouseEvent.getSource();
        if (Objects.equals(b.getText(), "Start")) {
            b.setText("Stop");
            setIconToButton(b, "images/stop.png");
            timer.play();
        } else {
            b.setText("Start");
            setIconToButton(b, "images/play.png");
            timer.pause();

        }

    }

    public void next() {
        boolean flag = false;
        if (timer.getStatus() == Animation.Status.RUNNING) {
            flag = true;
            timer.pause();
        }
        MainMenu.get_BL().updateBoard();
        ArrayList<Integer> cells = Helper.O_to_Int();
        update_cells(cells);
        int count = Integer.parseInt(generation.getText());
        count++;
        generation.setText(String.valueOf(count));
        if (flag)
            timer.play();
    }

    public void reset() {
        clear();
        MainMenu.get_BL().Reset();
        ArrayList<Integer> cells = Helper.O_to_Int();
        for (int i = 0; i < cells.size(); i += 2) {
            Rectangles[cells.get(i)][cells.get(i + 1)].setFill(Color.BLUE);
        }
        speedBar.setValue(1);
        zoombar.setValue(1);
        pause();
        generation.setText("0");

    }

    private void pause() {
        if (Objects.equals(start.getText(), "Stop")) {
            start.setText("Start");
            setIconToButton(start, "images/play.png");
            timer.pause();
        }
    }

    public void clear() {
        MainMenu.get_BL().Clear();
        ArrayList<Integer> cells = Helper.O_to_Int();
        for (int i = 0; i < cells.size(); i += 2) {
            Rectangles[cells.get(i)][cells.get(i + 1)].setFill(Color.valueOf("#b0c4de"));
        }
        pause();
    }

    public void saveState() {
        boolean flag = false;
        if (timer.getStatus() == Animation.Status.RUNNING) {
            flag = true;
            timer.pause();
        }
        TextInputDialog dialog = new TextInputDialog("My state");
        dialog.setTitle("SAVE STATE");
        dialog.setHeaderText("Save Current State of Grid");
        dialog.setContentText("Please enter State name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(s -> {

            try {
                Helper.write_argument(s, "input1");
                MainMenu.get_BL().save();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        if (flag)
            timer.play();
    }

}
