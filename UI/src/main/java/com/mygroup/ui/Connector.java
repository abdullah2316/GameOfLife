package com.mygroup.ui;

import java.util.ArrayList;

public class Connector {
    //private final static Connector instance = new Connector();
    public static char data;
    public static ArrayList<Integer> coordinates_data;
    public static ArrayList<StringBuilder> date_time;
    public static ArrayList<Integer> currentAlive;
    //public static driver obj;

    public Connector() {
        coordinates_data = new ArrayList<>();
        date_time = new ArrayList<>();
        currentAlive = new ArrayList<>();
        currentAlive.add(10);
        currentAlive.add(20);
        currentAlive.add(10);
        currentAlive.add(21);

    }

    public void reset() {

    }

}
