import java.sql.SQLException;
import java.util.ArrayList;

public interface DBduties {
   
    public void save(String Name, int Generation, ArrayList<Integer> array) throws SQLException;
    public  void Load(ArrayList<StringBuilder> info, ArrayList<Integer> cells);
    public  void deletestate(int id);
}
