import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521/XEPDB1",
                "ahamed",
                "Ahamed@123"
        );

        return con;
    }
}