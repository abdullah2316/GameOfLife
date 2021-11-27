import org.jetbrains.annotations.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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

    public StringBuilder[][] viewSavedStates() throws SQLException {
        String query = "Select * from SaveInfo";
        ResultSet m_ResultSet = m_Statement.executeQuery(query);
        int count = getSaves();
        StringBuilder[][] array = new StringBuilder[count][];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 5; j++) {
                array[i][j] = new StringBuilder("");
            }
        }
        int i = 0;
        while (m_ResultSet.next()) {
            array[i][0].append(m_ResultSet.getInt(1));
            array[i][1].append(m_ResultSet.getString(2));
            array[i][2].append(m_ResultSet.getString(3));
            array[i][3].append(m_ResultSet.getString(4));
            array[i][4].append(m_ResultSet.getString(5));
                
            i += 1;
        }
        return array;
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

    public int[] load(int Save) throws SQLException {
        if(getSaves() < Save) return new int[]{0};
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


    public void save(String Name, int Generation, @NotNull ArrayList<Integer> array) throws SQLException {
        String query = "SELECT Max([Save]) FROM Cells";
        ResultSet m_ResultSet = m_Statement.executeQuery(query);
        int Save;
        if (!m_ResultSet.next())
            Save = 1;
        else
            Save = m_ResultSet.getInt(1) + 1;

        for (int  i = 0 ; i < array.size() ; i += 2) {
            m_Statement.executeUpdate("INSERT INTO Cells VALUES (" + Save + ", " + valueOf(array.get(i)) + "," + valueOf(array.get(i + 1)) + ")");
        }
        String query2 = "INSERT INTO SAVE_INFO VALUES ( " + Save + ", '" + Name + "', " + Generation +", NULL , NULL)";
        m_Statement.executeUpdate(query2);
        // TIME FORMAT: 00:01:14.341,  DATE FORMAT: 2017-01-23
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
