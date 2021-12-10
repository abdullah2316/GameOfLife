package BL;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataHandler implements Data {


    @Override
    public void Load(ArrayList<StringBuilder> info, ArrayList<Integer> cells) throws SQLException, ClassNotFoundException, FileNotFoundException {
        GameLogic.getDB().Load(info, cells);
    }

    @Override
    public void deletestate(String id) throws SQLException, ClassNotFoundException {
        GameLogic.getDB().deletestate(id);
    }


    public void save(String Name, int Generation, ArrayList<Integer> array) throws SQLException, ClassNotFoundException {
        GameLogic.getDB().save(Name, Generation, array);
    }


    public ArrayList<Integer> Load_A_State(String id) throws SQLException, ClassNotFoundException, FileNotFoundException {
        return GameLogic.getDB().LoadState(id);
    }

}
