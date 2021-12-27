package BL;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public interface GameUI {
    //public void save(String Name) throws SQLException, ClassNotFoundException;


    //takes row and col of new click and returns updated array of changed cells
    void isClicked();

    // save State in DB

    //simple update called after certain interval of time
    void updateBoard();//ok

    //clear board and data
    void Clear();//ok

    void Reset();//ok

    //reload the initially
    void ConstructBoard();

    //
    void save() throws SQLException, ClassNotFoundException;

    //
    void Load_A_State() throws SQLException, ClassNotFoundException, FileNotFoundException;

    //
    void start();

    //ArrayList<Integer> start();
    void get_Alive();
}
