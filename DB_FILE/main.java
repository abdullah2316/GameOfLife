import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.reflect.Array;

public class Main {
    public static void main (String[] args) throws SQLException, IOException {
        ArrayList<Integer> Arr = new ArrayList<Integer>();
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
        DB_Filing Fil = new DB_Filing();
        Fil.save_into_file("abc", 1, Arr);
        Fil.save_into_file("Abdullah", 5, Arr);
    }
}
