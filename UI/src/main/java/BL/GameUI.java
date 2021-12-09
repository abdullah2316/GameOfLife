package BL;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GameUI {
    //public void save(String Name) throws SQLException, ClassNotFoundException;


    //takes row and col of new click and returns updated array of changed cells
    void isClicked(int row, int col);

    // save State in DB

    //simple update called after certain interval of time
    ArrayList<Integer> updateBoard();//ok

    //clear board and data
    ArrayList<Integer> Clear();//ok

    ArrayList<Integer> Reset();//ok

    //reload the initially
    ArrayList<Integer> ConstructBoard();

    //
    public void save(String Name) throws SQLException, ClassNotFoundException;

    //
    public void Load_A_State(String id) throws SQLException, ClassNotFoundException, FileNotFoundException;

    //
    ArrayList<Integer> start();

    ArrayList<Integer> get_Alive();
}
