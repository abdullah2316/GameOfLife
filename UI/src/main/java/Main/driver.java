package Main;

import BL.DBduties;
import BL.DataHandler;
import BL.GameLogic;
import DB.DBimpl;
import DB_FILE.DB_Filing;
import UI_Console.mainmenu;
import com.mygroup.ui.MainMenu;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class driver {

    private static GameLogic BL;
    private static DataHandler DH;
    private static DBduties Dt;

    public static void main(String[] args) throws SQLException, FileNotFoundException, InterruptedException, ClassNotFoundException {
        System.out.println("Choose Storage:\n1-SQL Server\n2-File");
        Scanner myObj = new Scanner(System.in);
        DH = new DataHandler();
        if (Objects.equals(myObj.next(), "1")) {
            Dt = new DBimpl();
        } else
            Dt = new DB_Filing();
        System.out.println("Choose Front End:\n1-GUI\n2-Console");
        if (Objects.equals(myObj.next(), "1")) {
            BL = new GameLogic(Dt);
            MainMenu m = new MainMenu(DH, BL);//GUI
            m.init(args);
        } else {
            BL = new GameLogic(Dt);
            mainmenu obj = new mainmenu(DH, BL);
            obj.start();
        }


    }


}
