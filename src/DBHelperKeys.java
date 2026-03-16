import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelperKeys {

    private static final String URL = "jdbc:sqlite:presetKeys.db";

    public static void init() {

        String sql = """
                CREATE TABLE IF NOT EXISTS presetKeys (
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

        String sql = "INSERT INTO presetKeys(value) VALUES(?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, key);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadKeys() {

        List<String> presetKeys = new ArrayList<>();

        String sql = "SELECT value FROM presetKeys";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                presetKeys.add(rs.getString("value"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return presetKeys;
    }

    public static boolean isDatabaseEmpty() {

        String sql = "SELECT COUNT(*) FROM presetKeys";

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

            stmt.executeUpdate("DELETE FROM presetKeys");
            stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='presetKeys'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void seedFromData() {

        for (String key : Data.presetKeys) {
            addKey(key);
            Util.print(key);
        }
    }
}