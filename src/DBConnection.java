import java.sql.*;

public class DBConnection {
    public static Connection connect() {
        try {
            // Connects to bills.db in your root folder
            Connection conn = DriverManager.getConnection("jdbc:sqlite:bills.db");
            System.out.println("✅ Database Connected Successfully!");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Database Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }
}
