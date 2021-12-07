package BL;

import Main.driver;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataHandler implements Data {


    @Override
    public void Load(ArrayList<StringBuilder> info, ArrayList<Integer> cells) throws SQLException, ClassNotFoundException, FileNotFoundException {
        driver.getDB().Load(info, cells);
    }

    @Override
    public void deletestate(String id) throws SQLException, ClassNotFoundException {
        driver.getDB().deletestate(id);
    }

    @Override
    public void save(String Name) throws SQLException, ClassNotFoundException {
        driver.getDB().save(Name, GameLogic.get_Generation(), GameLogic.get_Board().get_Alive());
    }

    @Override
    public void Load_A_State(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        GameLogic.set_Board(driver.getDB().LoadState(id));
    }

}
