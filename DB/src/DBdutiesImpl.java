import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBdutiesImpl implements DBduties {
    public void connect()  {
        try
        {
            String url = "jdbc:sqlserver://DESKTOP-TUFK8QV;Database=lab5E;IntegratedSecurity=true"; //connection string here test is the name of the database
            Connection con = DriverManager.getConnection(url); // pass the connection string, username and password
            System.out.println("connection established "+con);
        }
        catch (SQLException e )
        {
            System.out.println(e);
        }

    }
    @Override
    public String load() {
        System.out.println("data loaded");
        return "data loaded";
    }

    @Override
    public void save() {
        System.out.println("data saved");

    }
}
