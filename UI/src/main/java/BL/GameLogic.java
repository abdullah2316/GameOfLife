package BL;

import DB.DBimpl;
import com.google.gson.Gson;
import com.mygroup.ui.Helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameLogic implements GameUI {
    private static DBduties DB;
    private static int count;
    private int r;
    private int c;
    private GameBoard Board;
    private int Generation;

    public GameLogic() {

        //DB = d;
        if (count == 0) {
            Gson gson = new Gson();
            try (Reader reader = new FileReader("\\AllModules\\DataExchange\\DB.json")) {
                DB = gson.fromJson(reader, DBimpl.class);
                reader.close();
                Helper.deleteFile("\\AllModules\\DataExchange\\DB.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        count++;
    }

    public static DBduties getDB() {
        return DB;
    }

    public void get_Alive() {
        BL.Helper.write_output_AL(Board.get_Alive());
    }

    public void set_Board(ArrayList<Integer> cells) {
        Board = new GameBoard(cells, r, c);

    }

    //construct board with initial pattern
    public void ConstructBoard() {


        r = BL.Helper.Arguments_to_int("input1");
        c = BL.Helper.Arguments_to_int("input2");
        Board = new GameBoard(r, c);
        Generation = 1;
        BL.Helper.write_output_AL(Board.Pattern());
    }

    @Override
    public void save() throws SQLException, ClassNotFoundException {
        String Name = BL.Helper.Arguments_to_string("input1");
        DataHandler dh = new DataHandler();
        BL.Helper.write_argument(Name, "input1");
        BL.Helper.write_argument(Generation, "input2");
        BL.Helper.write_argument(Board.get_Alive(), "input3");
        dh.save();
    }

    @Override
    public void Load_A_State() throws SQLException, ClassNotFoundException, FileNotFoundException {

        r = BL.Helper.Arguments_to_int("input1");
        c = BL.Helper.Arguments_to_int("input2");
        DataHandler dh = new DataHandler();
        dh.Load_A_State();
        set_Board(BL.Helper.O_to_Int());
    }

    public void isClicked() {
        int row = BL.Helper.Arguments_to_int("input1");
        int column = BL.Helper.Arguments_to_int("input2");
        Board.isClicked(row, column);
    }

    public void updateBoard() {
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
        BL.Helper.write_output_AL(arr);
        // return arr;
    }

    public void start() {
        BL.Helper.write_output_AL(Board.get_Alive());
    }

    public void Reset() {
        Generation = 1;
        BL.Helper.write_output_AL(Board.Pattern());
    }

    public void Clear() {
        BL.Helper.write_output_AL(Board.ClearBoard());

    }
}
