package com.mygroup.ui;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SavedStates {

    public Label title;
    public TilePane grid_internal;

    public void initialize()
    {
        //setting up grid
        for (int i=0;i<100;i+=20)
        {
            for (int j=0;j<1500;j+=20)
            {
                Rectangle r= new Rectangle();
                r.setWidth(20);
                r.setHeight(20);
                r.setFill(Color.rgb(126,126,126));
                r.setStroke(Color.rgb(153,153,153));
                r.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent t) {
                        r.setFill(Color.YELLOW);
                    }
                });
                grid_internal.getChildren().add(r);
            }
        }
    }

}
