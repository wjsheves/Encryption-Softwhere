import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    private static final String URL = "jdbc:sqlite:keys.db";

    public static void init() {

        String sql = """
                CREATE TABLE IF NOT EXISTS keys (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    value TEXT NOT NULL
                );
                """;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addKey(String key) {

        String sql = "INSERT INTO keys(value) VALUES(?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, key);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadKeys() {

        List<String> keys = new ArrayList<>();

        String sql = "SELECT value FROM keys";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                keys.add(rs.getString("value"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return keys;
    }

    public static boolean isDatabaseEmpty() {

        String sql = "SELECT COUNT(*) FROM keys";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.getInt(1) == 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void clearKeys() {

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DELETE FROM keys");
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='keys'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void seedFromData() {

        for (String key : Data.keys) {
            addKey(key);
        }
    }
}