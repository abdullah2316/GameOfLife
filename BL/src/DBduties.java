import java.sql.SQLException;
import java.util.ArrayList;

public interface DBduties {
    public int[] load(int Save) throws SQLException;
    public void save(String Name, ArrayList<Integer> array) throws SQLException;
}
