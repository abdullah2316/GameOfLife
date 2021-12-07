package BL;

import java.util.ArrayList;

public class GameLogic implements GameUI {
    private static GameBoard Board;
    private static int Generation;

    public GameLogic() {

    }

    public static int get_Generation() {
        return Generation;
    }

    public static GameBoard get_Board() {
        return Board;
    }

    public static void set_Board(ArrayList<Integer> cells) {
        Board = new GameBoard(cells);

    }

    //construct board with initial pattern
    public ArrayList<Integer> ConstructBoard() {
        Board = new GameBoard();
        Generation = 1;
        return Board.Pattern();
    }

    public void isClicked(int row, int column) {
        Board.isClicked(row, column);
    }

    public ArrayList<Integer> updateBoard() {
        ArrayList<Integer> arr = new ArrayList<>();
        GameBoard temp = new GameBoard();
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
