package UI_Console;

import BL.Data;
import BL.DataHandler;
import BL.GameLogic;
import BL.GameUI;
import com.google.gson.Gson;
import com.mygroup.ui.Helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class mainmenu {
    private static Data datafuncs;
    private static GameUI gameFuncs;
    private static int count;

    public mainmenu() {

        if (count == 0) {
            Gson gson = new Gson();
            try (Reader reader = new FileReader("\\AllModules\\DataExchange\\DH.json")) {
                datafuncs = gson.fromJson(reader, DataHandler.class);
                reader.close();
                Helper.deleteFile("\\AllModules\\DataExchange\\DH.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (Reader reader = new FileReader("\\AllModules\\DataExchange\\BL.json")) {
                gameFuncs = gson.fromJson(reader, GameLogic.class);
                reader.close();
                Helper.deleteFile("\\AllModules\\DataExchange\\BL.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        count++;
    }

    public static Data get_BLD() {
        return datafuncs;
    }

    public static GameUI get_BL() {
        return gameFuncs;
    }

    public String show() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("----- GAME OF LIFE-----\n\n");
        System.out.println("Choose an option:");
        System.out.println("1-New State");
        System.out.println("2-View Saved State");
        System.out.println("3-Load State");
        System.out.println("4-Delete State");
        System.out.println("5-Exit");
        return myObj.nextLine();
    }

    public void start() throws InterruptedException, SQLException, FileNotFoundException, ClassNotFoundException {

        String choice = "1";
        while (!Objects.equals(choice, "5")) {
            choice = show();
            switch (choice) {
                case "1":
                    System.out.println("new");
                    GameScreen.init(0);
                    break;
                case "2":
                    System.out.println("view");
                    SavedStates viewstates = new SavedStates(0);
                    viewstates.init();
                    break;
                case "3":
                    System.out.println("load");
                    SavedStates loadstate = new SavedStates(1);
                    loadstate.init();
                    GameScreen.init(1);
                    break;
                case "4":
                    System.out.println("delete");
                    SavedStates delState = new SavedStates(2);
                    delState.init();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
        System.out.println("BYE!");

    }

}
