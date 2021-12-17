package BL;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameLogic implements GameUI {
    private static DBduties DB;
    private int r;
    private int c;
    private GameBoard Board;
    private int Generation;

    public GameLogic(DBduties d) {

        DB = d;
    }

    public static DBduties getDB() {
        return DB;
    }

    public ArrayList<Integer> get_Alive() {
        return Board.get_Alive();
    }

    public void set_Board(ArrayList<Integer> cells) {
        Board = new GameBoard(cells, r, c);

    }

    //construct board with initial pattern
    public ArrayList<Integer> ConstructBoard(int row, int col) {
        r = row;
        c = col;
        Board = new GameBoard(r, c);
        Generation = 1;
        return Board.Pattern();
    }

    @Override
    public void save(String Name) throws SQLException, ClassNotFoundException {
        DataHandler dh = new DataHandler();
        dh.save(Name, Generation, Board.get_Alive());
    }

    @Override
    public void Load_A_State(String id, int row, int col) throws SQLException, ClassNotFoundException, FileNotFoundException {
        r = row;
        c = col;
        DataHandler dh = new DataHandler();
        set_Board(dh.Load_A_State(id));
    }

    public void isClicked(int row, int column) {
        Board.isClicked(row, column);
    }

    public ArrayList<Integer> updateBoard() {
        ArrayList<Integer> arr = new ArrayList<>();
        GameBoard temp = new GameBoard(r, c);
        temp.copy(Board);
        int row = Board.getRowSize();
        int col = Board.getColSize();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int Neighbor_Count = Board.check_neighbor(i, j);

                if ((Neighbor_Count == 3) && !Board.isAlive(i, j)) {
                    temp.isClicked(i, j);
                    arr.add(i);         //row
                    arr.add(j);         //col of Life
                } else if ((Neighbor_Count < 2 || Neighbor_Count > 3) && Board.isAlive(i, j)) {
                    temp.isClicked(i, j);
                    arr.add(i * -1);
                    arr.add(j * -1);    // col for death
                }
            }
        }
        Board.copy(temp);
        Generation++;
        return arr;
    }

    public ArrayList<Integer> start() {
        return Board.get_Alive();
    }

    public ArrayList<Integer> Reset() {
        Generation = 1;
        return Board.Pattern();
    }

    public ArrayList<Integer> Clear() {
        return Board.ClearBoard();
    }
}
