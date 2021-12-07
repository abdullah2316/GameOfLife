package com.mygroup.ui;

import java.util.ArrayList;

public class Connector {
    //private final static Connector instance = new Connector();
    public static char flag1;//load or view or delete?
    public static ArrayList<Integer> coordinates_data;
    public static ArrayList<StringBuilder> state_info;
    public static ArrayList<Integer> currentAlive;

    //public static driver obj;
    public Connector() {
        coordinates_data = new ArrayList<>();
        state_info = new ArrayList<>();
        currentAlive = new ArrayList<>();
    }

    public static void reset() {
        currentAlive.clear();
        state_info.clear();
        coordinates_data.clear();
        flag1 = 'x';
    }

}
