import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.reflect.Array;

public class Main {
    public static void main (String[] args) throws SQLException, IOException {
        ArrayList<Integer> Arr = new ArrayList<Integer>();
        ArrayList<Integer> Arr2 = new ArrayList<Integer>();
        Arr.add(2);
        Arr.add(4);
        Arr.add(3);
        Arr.add(4);
        Arr.add(4);
        Arr.add(4);
        Arr.add(3);
        Arr.add(5);
        Arr.add(3);
        Arr.add(3);

        Arr2.add(2);
        Arr2.add(4);
        Arr2.add(3);
        Arr2.add(4);
        Arr2.add(4);
        Arr2.add(4);
        Arr2.add(3);
        Arr2.add(5);

        ArrayList<Integer> cells = new ArrayList<Integer>();
        ArrayList<StringBuilder> info = new ArrayList<StringBuilder>();
        DB_Filing Fil = new DB_Filing();
        Fil.save("abc", 1, Arr);
        Fil.save("Abdullah", 5, Arr2);
        Fil.Load(info, cells);
        Fil.deletestate("State2.txt");
        Fil.save("ggggg", 8, Arr2);
    }
}
