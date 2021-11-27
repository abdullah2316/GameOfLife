import java.sql.SQLException;
import java.util.ArrayList;

public interface DBduties {
    public int[] load(int Save) throws SQLException;
    public void save(String Name, int Generation, ArrayList<Integer> array) throws SQLException;
}
