package Main;

import BL.DBduties;
import BL.DataHandler;
import BL.GameLogic;
import DB.DBimpl;
import DB_FILE.DB_Filing;
import com.mygroup.ui.MainMenu;
import javafx.application.Application;

public class driver {
    private static DBimpl DB;
    private static GameLogic BL;
    private static DataHandler DH;
    private static DB_Filing DF;

    public static void main(String[] args) {
        DB = new DBimpl();
        BL = new GameLogic();
        DH = new DataHandler();
        DF = new DB_Filing();
        System.out.println("init called\n");
        Application.launch(MainMenu.class, args);

    }

    public static DataHandler getBLD() {

        return DH;
    }

    public static DBduties getDB() {

        return DF;
    }

    public static GameLogic getBL() {

        return BL;
    }

    public static void init() {
//        DB = new DBimpl();
//        BL = new GameLogic();
//        DH = new DataHandler();
//        DF = new DB_Filing();
//        System.out.println("init called\n");
    }
}
