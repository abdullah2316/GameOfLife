package BL;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public interface DBduties {

    void save() throws SQLException, ClassNotFoundException;

    void Load() throws SQLException, ClassNotFoundException, FileNotFoundException;

    void deletestate() throws SQLException, ClassNotFoundException;

    void LoadState() throws SQLException, ClassNotFoundException, FileNotFoundException;
}
