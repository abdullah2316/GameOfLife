import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;

public class GameLogic implements GameUI {
    private GameBoard Board;
    private boolean State;
    private int Zoom_Factor;
    private int Counter;
    private int Speed;
    private int SpeedLimit;

    public GameLogic(){
        Zoom_Factor = 1;
        Counter = 0;
        Speed = 1;
        SpeedLimit = 10;
        State = true;
    }
    public void ConstructBoard(){
        Board = new GameBoard();
    }
    public void displayBoard(){
        Board.displayBoard();
    }

    public void isClicked(int row, int column) {
        Board.isClicked(row, column);
    }


    public void Save(String Name, @NotNull DBdutiesImpl object) throws SQLException {
        object.save(Name, Board.copyToArray());
    }

    public void LoadState(int Save, @NotNull DBdutiesImpl object) throws SQLException {
        Board.copyfromArray(object.load(Save));
    }

    public void DeleteState(int Save){

    }
    
    public ArrayList<Integer> updateBoard() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        GameBoard temp = new GameBoard(Board.getSize());
        temp.copy(Board);
        int size = Board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
        Counter++;
        return arr;
    }

    public void incrementSpeed(){
        if (Speed < SpeedLimit)
            Speed++;
    }
    public void decrementSpeed(){
        if (Speed > 1)
            Speed--;
    }
    public void ResetGrid(){
        Board.ResetBoard();
    }
    public void Start(){
        State = true;
    }
    public void Stop(){
        State = false;
    }

    public void Reset(){
        Zoom_Factor = 1;
        Counter = 0;
        Speed = 1;
        Board.ResetBoard();
    }
    public void Clear() {
        Board.ClearBoard();
    }
}
