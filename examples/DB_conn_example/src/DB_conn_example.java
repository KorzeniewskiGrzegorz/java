
import java.sql.*;

 
/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class DB_conn_example {
 
    public static void main(String[] args) {
 
        Connection conn = null;
 
        try {
 
            String dbURL = "jdbc:sqlserver://sassdev.dyndns.org;databaseName=certus";
            String user = "Grzegorz";
            String pass = "Grzegorz123!";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {

                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT TOP 2 * FROM sass_labels;");
                
                while (rs.next()) {
                	  String lastName = rs.getString("status");
                	  System.out.println(lastName + "\n");
                	}
                
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}