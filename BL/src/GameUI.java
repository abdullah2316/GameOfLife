import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
    public interface GameUI {
        //takes row and col of new click and returns updated array of changed cells
        void isClicked(int row, int col);

        //simple update called after certain interval of time
        ArrayList<Integer> updateBoard();

        // save State in DB
        void Save(String name, @NotNull DBdutiesImpl object) throws SQLException;

        //clear board and data
        void Clear();
        void Reset();
        //reload the initially
}
