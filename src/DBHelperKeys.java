import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelperKeys {

    private static final String DB_FOLDER =
            System.getProperty("user.home") + "/EncryptionApp";

    private static final String URL =
            "jdbc:sqlite:" + DB_FOLDER + "/presetKeys.db";

    private static void ensureFolderExists() {

        File folder = new File(DB_FOLDER);

        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void init() {

        ensureFolderExists();

        String sql = """
            CREATE TABLE IF NOT EXISTS presetKeys (
                name TEXT PRIMARY KEY,
                value TEXT NOT NULL,
                isPreset INTEGER NOT NULL
            );
            """;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        seedFromData();
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
            stmt.executeUpdate("DELETE FROM presetKeys WHERE name='presetKeys'");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void addPresetKey(String name, String value) {

        String sql = """
        INSERT INTO presetKeys(name, value, isPreset)
        VALUES(?, ?, 1)
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

    public static void addUserKey(String name, String value) {

        String sql = """
        INSERT INTO presetKeys(name, value, isPreset)
        VALUES(?, ?, 0)
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

    public static void clearKeysFromData() {

        String sql = "DELETE FROM presetKeys WHERE isPreset = 1";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void seedFromData() {
        clearKeys();

        int i = 1;
        for (String key : Data.presetKeys) {
            addPresetKey("Data_"+i,key);
            Util.print(key);
            i++;
        }
    }
}