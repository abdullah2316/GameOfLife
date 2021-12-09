package Main;

import BL.*;
import DB.DBimpl;
import DB_FILE.DB_Filing;
import UI_Console.mainmenu;
import com.mygroup.ui.MainMenu;

import java.util.Objects;
import java.util.Scanner;

public class driver {
    private static DBimpl DB;
    private static GameLogic BL;
    private static DataHandler DH;
    private static DB_Filing DF;
    private static DBduties Dt;

    public static void main(String[] args) {
        System.out.println("Choose Storage:\n1-SQL Server\n2-File");
        Scanner myObj = new Scanner(System.in);
        DH = new DataHandler();
        if (Objects.equals(myObj.next(), "1")) {
            Dt = new DBimpl();
        } else
            Dt = new DB_Filing();
        System.out.println("Choose Front End:\n1-GUI\n2-Console");
        if (Objects.equals(myObj.next(), "1")) {
            BL = new GameLogic(40, 100, Dt);
            MainMenu m = new MainMenu(DH, BL);//GUI
            m.init();
        } else {
            BL = new GameLogic(20, 20, Dt);
            mainmenu obj = new mainmenu(DH, BL);
        }


    }

    public static DBduties getDB() {

        return Dt;
    }

    public static Data getBLD() {

        return DH;
    }

    public static GameUI getBL() {

        return BL;
    }

    public static void init() {
//        DB = new DBimpl();
//        BL = new GameLogic(20, 20);
//        DH = new DataHandler();
//        DF = new DB_Filing();
//        System.out.println("init called\n");
    }
}
