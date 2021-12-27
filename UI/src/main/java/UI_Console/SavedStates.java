package UI_Console;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SavedStates {
    private int mode;
    private ArrayList<String> statesID;

    public SavedStates(int mode) {
        this.mode = mode;
        statesID = new ArrayList<>();
    }

    public void init() throws SQLException, FileNotFoundException, ClassNotFoundException {
        ArrayList<Integer> cells = new ArrayList<>();
        ArrayList<StringBuilder> info;

        mainmenu.get_BLD().Load();
        info = Helper.Arguments_to_SB("output1");
        cells = Helper.Arguments_to_AL("output2");
        System.out.println("herereer");
        for (int i = 0; i < (info.size() / 5); i++) {
            System.out.println(String.valueOf(i + 1) + "-" + (info.get(i * 5)));
            System.out.println("Date Saved: " + info.get((i * 5) + 1));
            System.out.println("Time Saved: " + info.get((i * 5) + 2));
            System.out.println("Generation: " + info.get((i * 5) + 3));
            statesID.add((info.get((i * 5) + 4)).toString());//state id
        }
        if (mode != 0) {
            if (mode == 1)
                System.out.println("Enter state Number to Load");
            else if (mode == 2)
                System.out.println("Enter State Number to DELETE");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            String choice = myObj.nextLine();
            if (Objects.equals(choice, "2"))//delete state
            {
                Helper.write_argument(statesID.get(Integer.parseInt(choice) - 1), "input1");
                mainmenu.get_BLD().deletestate();
            } else if (Objects.equals(choice, "1"))//Load State
            {
                Helper.write_argument(20, "input1");
                Helper.write_argument(20, "input2");
                Helper.write_argument(statesID.get(Integer.parseInt(choice) - 1), "input1");
                mainmenu.get_BL().Load_A_State();
            }
        }
    }


}
