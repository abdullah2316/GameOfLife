package com.mygroup.ui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

public class Helper {
    //private final static Connector instance = new Connector();
    public static char flag1;//load or view or delete?
    public static ArrayList<Integer> coordinates_data;
    public static ArrayList<StringBuilder> state_info;
    public static ArrayList<Integer> currentAlive;

    //public static driver obj;
    public Helper() {
        coordinates_data = new ArrayList<>();
        state_info = new ArrayList<>();
        currentAlive = new ArrayList<>();
    }

    public static ArrayList<StringBuilder> Arguments_to_SB(String File) {
        ArrayList<StringBuilder> out = new ArrayList<>();
        Gson gson = new Gson();
        try (Reader reader = new FileReader("\\AllModules\\DataExchange\\" + File + ".json")) {
            out = gson.fromJson(reader, new TypeToken<ArrayList<StringBuilder>>() {
            }.getType());
            reader.close();
            deleteFile("\\AllModules\\DataExchange\\" + File + ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static ArrayList<Integer> Arguments_to_AL(String File) {
        ArrayList<Integer> out = new ArrayList<>();
        Gson gson = new Gson();
        try (Reader reader = new FileReader("\\AllModules\\DataExchange\\" + File + ".json")) {
            out = gson.fromJson(reader, new TypeToken<ArrayList<Integer>>() {
            }.getType());
            reader.close();
            deleteFile("\\AllModules\\DataExchange\\" + File + ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static void deleteFile(String name) {
        File myObj = new File(name);
        if (!myObj.delete()) {
            System.out.println("Failed to delete the file." + myObj.getName());
        }
    }

    public static <T> void write_argument(T arg, String File) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("\\AllModules\\DataExchange\\" + File + ".json")) {
            gson.toJson(arg, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> O_to_Int() {
        ArrayList<Integer> out = new ArrayList<>();
        Gson gson = new Gson();
        try (Reader reader = new FileReader("\\AllModules\\DataExchange\\output.json")) {
            out = gson.fromJson(reader, new TypeToken<ArrayList<Integer>>() {
            }.getType());
            reader.close();
            deleteFile("\\AllModules\\DataExchange\\output.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}
