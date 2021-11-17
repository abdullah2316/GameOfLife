package com.mygroup.ui;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

public class GameScreenController {

    public Pane header;
    public Label temp;
    public ImageView img;
    public Button btn;
    public VBox vb;
    public Pane grid_internal;
    public BorderPane grid_ext;
    public ScrollPane scroller;
    public Slider zoombar;
   private int center_indexR;
   private int center_indexC;
    private double height=400;//actual height of pane on screen
    private double logical_height;//logical height of pane i.e scrollable
   private double logical_width;//logical width of pane i.e scrollable
   private double width=1340;//actual width of pane on screen
   private int Total_columns =100;//total columns
   private int columsOnScreen=50;//at zoom=1
   private double square_size=width/ columsOnScreen;
   private int Total_rows = 50;
   private int rowsOnScreen=30;//at zoom=1

    public void initialize() {
        Image image_header;
        //setting header image
        image_header = new Image(Objects.requireNonNull(this.getClass().getResource("images/header.png")).toString());
        img.setImage(image_header);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        System.out.println(primaryScreenBounds.getWidth());
        //setting up grid
        logical_width=CalcLogicalwidth(Total_columns);
        logical_height=CalcLogicalHeight(Total_rows);
        System.out.println(logical_height);
        System.out.println(logical_width);
        for (double i=0;i<logical_height;i+=square_size)
        {
            System.out.println(" i: "+i);
            for (double j=0;j<logical_width;j+=square_size)
            {
                Rectangle r= new Rectangle(j,i,square_size,square_size);
                r.setFill(Color.rgb(126,126,126));
                r.setStroke(Color.rgb(153,153,153));
                r.setOnMouseClicked(t -> r.setFill(Color.YELLOW));
                grid_internal.getChildren().add(r);
            }
        }
        //panning grid
        scroller.setPannable(true);

        scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroller.setPrefViewportHeight(400);
        //setting zoom functionality
        zoombar.setMax(2);
        zoombar.setMin(0.5);
        zoombar.setValue(1);
        grid_internal.scaleXProperty().bind(zoombar.valueProperty());
        grid_internal.scaleYProperty().bind(zoombar.valueProperty());
    }

    public void back() throws Exception {
        MainMenu obj = new MainMenu();
        Stage stageTheLayoutBelongs = (Stage) header.getScene().getWindow();
        Scene scene = stageTheLayoutBelongs.getScene();
        obj.change_scene(stageTheLayoutBelongs, scene, "MainMenu.fxml", true, "mainmenustyle.css");
    }
    public double CalcLogicalwidth(int columns)
    {
        return square_size*columns;
    }
    public double CalcLogicalHeight(int rows)
    {
        return square_size*rows;
    }
}
