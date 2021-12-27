package Main;

import BL.DBduties;
import BL.DataHandler;
import BL.GameLogic;
import DB.DBimpl;
import DB_FILE.DB_Filing;
import UI_Console.mainmenu;
import com.google.gson.Gson;
import com.mygroup.ui.MainMenu;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class driver {

    public static void main(String[] args) throws SQLException, FileNotFoundException, InterruptedException, ClassNotFoundException {

        Scanner myObj = new Scanner(System.in);
        DataHandler DH = new DataHandler();
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("\\AllModules\\DataExchange\\DH.json")) {
            gson.toJson(DH, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Choose Storage:\n1-SQL Server\n2-File");
        DBduties dt;
        if (Objects.equals(myObj.next(), "1")) {
            dt = new DBimpl();
        } else
            dt = new DB_Filing();

        try (FileWriter writer = new FileWriter("\\AllModules\\DataExchange\\DB.json")) {
            gson.toJson(dt, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameLogic BL;
        BL = new GameLogic();
        try (FileWriter writer = new FileWriter("\\AllModules\\DataExchange\\BL.json")) {
            gson.toJson(BL, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Choose Front End:\n1-GUI\n2-Console");
        if (Objects.equals(myObj.next(), "1")) {
            MainMenu m = new MainMenu();//GUI
            m.init(args);
        } else {
            mainmenu obj = new mainmenu();
            obj.start();
        }


    }


}
