package com.mygroup.ui;


import BL.test;
import Main.driver;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
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
    private HBox speedZoom;
    @FXML
    private Pane grid_internal;
    @FXML
    private BorderPane grid_ext;
    @FXML
    private ScrollPane scroller;
    @FXML
    private Slider zoombar;
    @FXML
    private Button start;
    @FXML
    private HBox bottombox;
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
    private int centeral_row;
    private int centeral_column;
    private Rectangle[][] Rectangles;

    public void initialize() {

        Rectangles = new Rectangle[total_rows][total_columns];
        Image image_header;
        //setting header image
        image_header = new Image(Objects.requireNonNull(this.getClass().getResource("images/header.png")).toString());
        img.setImage(image_header);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        System.out.println(primaryScreenBounds.getWidth());
        //setting up grid
        //logical width of pane i.e scrollable
        double logical_width = CalcLogicalwidth(total_columns);
        //logical height of pane i.e scrollable
        double logical_height = CalcLogicalHeight(total_rows);
        double Y_coordinate = 0;
        for (int row_index = 0; row_index < total_rows; row_index = (int) Math.round(Y_coordinate / square_size)) {
            double X_coordinate = 0;
            for (int col_index = 0; col_index < total_columns; col_index = (int) Math.round(X_coordinate / square_size)) {
                Rectangle r = new Rectangle(X_coordinate, Y_coordinate, square_size, square_size);
                r.setFill(Color.valueOf("#b0c4de"));
                r.setStroke(Color.valueOf("#1e90ff"));
                r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (r.getFill() != Color.BLUE)
                            r.setFill(Color.BLUE);
                        else
                            r.setFill(Color.valueOf("#b0c4de"));
                        String row = r.getId().substring(0, (r.getId().indexOf('+')));
                        System.out.println(row + "--");
                        String col = r.getId().substring((r.getId().indexOf('+') + 1));
                        System.out.println(col + "--");
                        driver.getBL().isClicked(Integer.parseInt(row), Integer.parseInt(col));
                    }
                });
                r.setId(String.valueOf(row_index) + "+" + String.valueOf(col_index));
                System.out.println(r.getId() + "--");
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
        driver.init();
        if (Connector.flag1 != 'l')
            fill_cells(driver.getBL().ConstructBoard());
        else
            fill_cells(driver.getBL().start());

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
            ArrayList<Integer> cells = driver.getBL().updateBoard();
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
        test.reset();
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

    public void Reset_Grid() {
    }

    private double CalcLogicalwidth(int columns) {
        return square_size * columns;
    }

    private double CalcLogicalHeight(int rows) {
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

    public void start(MouseEvent mouseEvent) throws InterruptedException {
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

    public void next(MouseEvent mouseEvent) {

    }

    public void reset(MouseEvent mouseEvent) {
        clear();
        ArrayList<Integer> cells = driver.getBL().Reset();
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
        ArrayList<Integer> cells = driver.getBL().Clear();
        for (int i = 0; i < cells.size(); i += 2) {
            Rectangles[cells.get(i)][cells.get(i + 1)].setFill(Color.valueOf("#b0c4de"));
        }
        pause();
    }

    public void saveState(MouseEvent mouseEvent) {
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
            System.out.println("state name: " + s);
            //bl_obj.save(s);
            try {
                driver.getBLD().save(s);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        if (flag)
            timer.play();
    }

    private void invert(Rectangle r) {
        if (r.getFill().equals(Color.BLUE)) {
            r.setFill(Color.valueOf("#b0c4de"));
        } else
            r.setFill(Color.BLUE);

    }
}
