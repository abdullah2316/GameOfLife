import java.sql.SQLException;
import java.util.ArrayList;

public interface DBduties {
    public int[] load(int Save) throws SQLException;
    void save(ArrayList<Integer> array) throws SQLException;
}
