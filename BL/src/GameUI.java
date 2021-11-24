import java.util.ArrayList;

public interface GameUI {
    //takes row and col of new click and returns updated array of changed cells
    ArrayList<Integer> UpdateOnClick(int row, int col);

    //simple update called after certain interval of time
    ArrayList<Integer> Update();

    // save State in DB
    void Save();

    //load initial pattern
    ArrayList<Integer> initialize();

    //clear board and data
    void Clear();

    //reload the initially drawn prebuilt pattern
    int[] reset();

}
