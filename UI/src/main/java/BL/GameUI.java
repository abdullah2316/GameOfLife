package BL;

import java.util.ArrayList;

public interface GameUI {
    //public void save(String Name) throws SQLException, ClassNotFoundException;

    //takes row and col of new click and returns updated array of changed cells
    void isClicked(int row, int col);

    //simple update called after certain interval of time
    ArrayList<Integer> updateBoard();//ok

    // save State in DB

    //clear board and data
    ArrayList<Integer> Clear();//ok

    ArrayList<Integer> Reset();//ok

    //reload the initially
    ArrayList<Integer> ConstructBoard();
}
