import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBHandler {

    // ✅ Create table if it doesn't exist
    public static void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS bills (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "units INTEGER, " +
                "amount REAL)";
        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("✅ Table 'bills' ensured in database.");
        } catch (SQLException e) {
            System.err.println("❌ Failed to create table: " + e.getMessage());
        }
    }

    public static void saveBill(int units, double amount) {
        String sql = "INSERT INTO bills(date, units, amount) VALUES (?, ?, ?)";
        String date = new SimpleDateFormat("dd-MM-yyyy hh:mm a").format(new Date());

        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, date);
            pstmt.setInt(2, units);
            pstmt.setDouble(3, amount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getHistory() {
        StringBuilder history = new StringBuilder();
        String sql = "SELECT * FROM bills ORDER BY id DESC";

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                history.append("[").append(rs.getString("date")).append("] ")
                        .append("Units: ").append(rs.getInt("units")).append(" | ")
                        .append("Bill: ₹").append(rs.getDouble("amount")).append("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return history.toString();
    }

    public static Map<String, Integer> getUsageData() {
        Map<String, Integer> usageData = new LinkedHashMap<>();
        String sql = "SELECT date, units FROM bills";

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usageData.put(rs.getString("date"), rs.getInt("units"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usageData;
    }
}
