package BL;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Data {


    void Load(ArrayList<StringBuilder> info, ArrayList<Integer> cells) throws SQLException, ClassNotFoundException, FileNotFoundException;

    void deletestate(String id) throws SQLException, ClassNotFoundException;


}
