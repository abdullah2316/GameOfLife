import java.util.ArrayList;

public interface MenuUI {
    void LoadSavedStates(ArrayList<StringBuilder> Date_Time, ArrayList<Integer> Coordinates);

    void DeleteState(int id);

    ArrayList<Integer> start();
}
