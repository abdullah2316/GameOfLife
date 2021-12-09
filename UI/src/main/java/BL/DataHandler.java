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


    public void save(String Name, int Generation, ArrayList<Integer> array) throws SQLException, ClassNotFoundException {
        driver.getDB().save(Name, Generation, array);
    }


    public ArrayList<Integer> Load_A_State(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return driver.getDB().LoadState(id);
    }

}
