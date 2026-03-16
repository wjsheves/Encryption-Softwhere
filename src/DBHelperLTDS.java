import java.sql.*;

public class DBHelperLTDS {

    private static final String URL = "jdbc:sqlite:ltds.db";

    public static void init() {

        String sql = """
            CREATE TABLE IF NOT EXISTS ltds (
                name TEXT PRIMARY KEY,
                value TEXT NOT NULL
            );
            """;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        intVars();
    }

    public static void setVar(String name, String value) {

        String sql = """
            INSERT INTO ltds(name, value)
            VALUES(?, ?)
            ON CONFLICT(name)
            DO UPDATE SET value = excluded.value;
            """;

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, value);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getVar(String name) {

        String sql = "SELECT value FROM ltds WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("value");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteVar(String name) {

        String sql = "DELETE FROM ltds WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dumpLTDS() {

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DELETE FROM ltds");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void intVars() {
        DBHelperLTDS.setVar("DevMode","false");
    }
}