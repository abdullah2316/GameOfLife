package BL;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DBduties {

    void save(String Name, int Generation, ArrayList<Integer> array) throws SQLException, ClassNotFoundException;

    void Load(ArrayList<StringBuilder> info, ArrayList<Integer> cells) throws SQLException, ClassNotFoundException, FileNotFoundException;

    void deletestate(String id) throws SQLException, ClassNotFoundException;

    ArrayList<Integer> LoadState(String id) throws SQLException, ClassNotFoundException, FileNotFoundException;
}
