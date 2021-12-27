package BL;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public interface Data {


    void Load() throws SQLException, ClassNotFoundException, FileNotFoundException;

    void deletestate() throws SQLException, ClassNotFoundException;


}
