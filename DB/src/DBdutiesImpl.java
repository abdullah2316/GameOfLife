import java.sql.*;
import java.util.ArrayList;
import static java.lang.String.valueOf;

public class DBdutiesImpl implements DBduties {
    Connection con;
    Statement m_Statement;
    public void connect()  {
        try
        {
            String url = "jdbc:sqlserver://HP-8300\\SQLEXPRESS;Database=SDA;IntegratedSecurity=true"; //connection string here test is the name of the database
            con = DriverManager.getConnection(url);         // pass the connection string, username and password
            m_Statement = con.createStatement();  //Statement is created on the connection to execute queries
        }
        catch (SQLException e )
        {
            System.out.println(e);
        }
    }

    int getSaves() throws SQLException {
        String query = "SELECT MAX([Save]) from Cells";
        ResultSet m_ResultSet = m_Statement.executeQuery(query);
        m_ResultSet.next();
        return m_ResultSet.getInt(1);
    }
    int getCount(int Save) throws SQLException {
        String query = "SELECT Count(*) FROM Cells where [Save] = " + valueOf(Save);
        ResultSet m_ResultSet = m_Statement.executeQuery(query);
        m_ResultSet.next();
        return m_ResultSet.getInt(1);
    }

    @Override
    public int[] load(int Save) throws SQLException {
        if(getSaves() < Save) return null;
        int Counter = ((getCount(Save)) * 2) + 1;
        int []array = new int[Counter]; //Count = Rows + Columns + 1 (for size)
        array[0] = Counter;
        String query = "SELECT * FROM Cells where [Save] = " + valueOf(Save);
        ResultSet m_ResultSet = m_Statement.executeQuery(query);
        int i = 1;
        while (m_ResultSet.next()) {
            array[i] = m_ResultSet.getInt(2);
            array[i] = m_ResultSet.getInt(3);
            i += 2;
        }
        return array;
    }

    @Override
    public void save(ArrayList<Integer> array) throws SQLException {
        String query = "SELECT Max([Save]) FROM Cells";
        ResultSet m_ResultSet = m_Statement.executeQuery(query);
        int Save = m_ResultSet.getInt(1) + 1;
        for (int  i = 0 ; i < array.size() ; i += 2)
            m_Statement.executeUpdate("INSERT INTO Cells " + "VALUES (" + valueOf(array.get(i)) + "," + valueOf(array.get(i+1)) + ")");
    }

    void PrintTable() throws SQLException {
        String query = "SELECT * FROM Cells";
        ResultSet m_ResultSet = m_Statement.executeQuery(query);

        while (m_ResultSet.next()) {
            System.out.println(m_ResultSet.getString(1) + ", " + m_ResultSet.getString(2) + ", "
                    + m_ResultSet.getString(3));
        }
    }
}
