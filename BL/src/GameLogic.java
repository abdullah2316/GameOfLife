import org.jetbrains.annotations.NotNull;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameLogic implements GameUI {
    private GameBoard Board;
    private boolean State;
    private int Generation;
    private int SpeedLimit;

    public GameLogic(){
        Generation = 1;
        SpeedLimit = 10;
        State = true;
    }
    public ArrayList<Integer> ConstructBoard(){
        Board = new GameBoard();
        return Board.Pattern();
    }
    public void displayBoard(){
        Board.displayBoard();
    }

    public void isClicked(int row, int column) {
        Board.isClicked(row, column);
    }

    public void ViewSavedStates() {

    }

    public void Save(String Name, @NotNull DBdutiesImpl object) throws SQLException {
        object.save(Name, Generation, Board.copyToArray());
    }

    public void LoadState(int Save, @NotNull DBdutiesImpl object) throws SQLException {
        Board.copyfromArray(object.load(Save));
    }

    public void DeleteState(int Save){

    }

    public ArrayList<Integer> updateBoard() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        GameBoard temp = new GameBoard(Board.getRowSize(), Board.getColSize());
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
                    arr.add(i * -1);    //-ve int row
                    arr.add(j * -1);    // col for death
                }
            }
        }
        Board.copy(temp);
        Generation++;
        return arr;
    }

    public void Start(){
        State = true;
    }
    public void Stop(){
        State = false;
    }

    public ArrayList<Integer> Reset() {
        Generation = 1;
        return Board.Pattern();
    }
    public ArrayList<Integer> Clear() {
        return Board.ClearBoard();
    }
}
