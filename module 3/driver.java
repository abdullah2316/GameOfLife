import BL.DBduties;
import BL.DataHandler;
import BL.GameLogic;
import DB_pkg.DB;
import ui.MainMenu;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class driver {

    private static GameLogic BL;
    private static DataHandler DH;
    private static DBduties Dt;

    public static void main(String[] args) throws SQLException, FileNotFoundException, InterruptedException, ClassNotFoundException {

        DH = new DataHandler();//BL
            Dt = new DB();//DB

            BL = new GameLogic(Dt);
            MainMenu m = new MainMenu(DH, BL);//UI
            m.init(args);


    }


}
