package DB;

import BL.DBduties;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBimpl implements DBduties {
    private static Connection conn = null;

    public static void connect() {
        try {
            String url = "jdbc:sqlserver://DESKTOP-TUFK8QV;Database=SDA;IntegratedSecurity=true"; //connection string here test is the name of the database
            conn = DriverManager.getConnection(url);         // pass the connection string, username and password
            Statement m_Statement = conn.createStatement();  //Statement is created on the connection to execute queries
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            connect();
            System.out.println("Select statement: " + queryStmt + "\n");
            //Create statement
            stmt = conn.createStatement();
            //Execute select (query) operation
            resultSet = stmt.executeQuery(queryStmt);
            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        //Return CachedRowSet
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            connect();
            //Create Statement
            stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }

    public void deletestate(String identity) throws SQLException, ClassNotFoundException {

        int id = Integer.parseInt(identity);
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM States\n" +
                        "         WHERE ID =" + id + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
        //Execute DELETE operation
        try {
            dbExecuteUpdate(updateStmt);
            updateStmt =
                    "BEGIN\n" +
                            "   DELETE FROM States_Info\n" +
                            "         WHERE ID =" + id + ";\n" +
                            "   COMMIT;\n" +
                            "END;";
            dbExecuteUpdate(updateStmt);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    @Override
    public ArrayList<Integer> LoadState(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> cells = new ArrayList<>();
        String selectStmt = "select * from States_info WHERE ID = " + id + "";
        ResultSet cellscoordinates = dbExecuteQuery(selectStmt);
        while (cellscoordinates.next()) {
            cells.add(cellscoordinates.getInt("row_c"));
            cells.add(cellscoordinates.getInt("col_c"));
        }
        return cells;
    }


    public void save(String Name, int generation, ArrayList<Integer> cells) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        java.util.Date date = new Date();
        String datetime = formatter.format(date);
        String datetoday = datetime.substring(0, datetime.indexOf(' '));
        String time = datetime.substring(datetime.indexOf(' ') + 1);
        String updateStmt =
                "INSERT INTO States (Name_s,Dt,tm,Generation,cells) VALUES ( " + "'" + Name + "', '" + datetoday + "' , '" + time + "' , " + generation + " , " + (cells.size() / 2) + ")";
        //Execute DELETE operation
        try {
            dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
        String selectStmt = "select top(1) ID from States where Dt =  '" + datetoday + "' and tm = '" + time + "'";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = dbExecuteQuery(selectStmt);
            int id = 0;
            if (rsEmp.next()) {
                id = rsEmp.getInt("ID");
                //your logic...
            }
            System.out.println("id " + id);
            for (int i = 0; i < cells.size(); i += 2) {
                String updateStmt2 =
                        "INSERT INTO States_Info (ID,row_c,col_c) VALUES ( " + id + ",'" + String.valueOf(cells.get(i)) + "' , '" + cells.get(i + 1) + "' " + ")";
                //Execute DELETE operation
                try {
                    dbExecuteUpdate(updateStmt2);

                } catch (SQLException e) {
                    System.out.print("Error occurred while INSERT Operation: " + e);
                    System.out.println(updateStmt2);
                    throw e;
                }
            }
        } catch (SQLException e) {
            System.out.println("While searching , an error occurred: " + e);
            //Return exception
            throw e;
        }


    }

    public void Load(ArrayList<StringBuilder> info, ArrayList<Integer> cells) throws SQLException, ClassNotFoundException {

        String selectStmt = "select * from States  ";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = dbExecuteQuery(selectStmt);
            selectStmt = "select count(*) as [count] from States  ";
            ResultSet counting = dbExecuteQuery(selectStmt);
            int count = 0;
            if (counting.next()) {
                count = counting.getInt("count");
            }

            cells.add(count);
            while (rsEmp.next()) {
                info.add(new StringBuilder(rsEmp.getString("Name_s")));
                info.add(new StringBuilder(rsEmp.getString("Dt")));
                info.add(new StringBuilder(rsEmp.getString("tm")));
                info.add(new StringBuilder(rsEmp.getString("Generation")));
                info.add(new StringBuilder(rsEmp.getString("ID")));
                //info.add(new StringBuilder(rsEmp.getString("cells")));
                int id = rsEmp.getInt("ID");
                selectStmt = "select * from States_info WHERE ID = " + id + "";
                ResultSet cellscoordinates = dbExecuteQuery(selectStmt);
                cells.add(rsEmp.getInt("cells"));
                while (cellscoordinates.next()) {
                    cells.add(cellscoordinates.getInt("row_c"));
                    cells.add(cellscoordinates.getInt("col_c"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("While searching , an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

}

