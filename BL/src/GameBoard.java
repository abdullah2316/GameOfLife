import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<List<Cell>> Cells;

    public GameBoard(){
        Cells = new ArrayList<List<Cell>>();
    }
    public void isClicked(int row, int column){
        Cells.get(row).get(column).ChangeState();
    }
    public boolean isAlive(int row, int column){
        return Cells.get(row).get(column).check_IsAlive();
    }
    public void ResetBoard(){

    }
}
