import java.util.ArrayList;
import java.util.List;

public class Data {

    static String hashToCheck = "Dev";
    static Boolean devModeEnabled = Boolean.parseBoolean(DBHelperLTDS.getVar("DevMode"));
    static Boolean typeChoseD = false;
    static Boolean typeChoseE = false;

    static String currentKey = "";

    static String sq1 = "{[e|rm/2/4/1|]}";
    static String sq2 = "{[e|rm/4/1/1|]}";
    static String sq3 = "{[e|rm/1/2/1|]}";
    static String sq4 = "{[e|rm/4/3/1|]}";
    static String sq5 = "{[e|rm/1/1/1|]}";

    public static List<String> presetKeys = new ArrayList<>();

    static {
        presetKeys.add(sq1);
        presetKeys.add(sq2);
        presetKeys.add(sq3);
        presetKeys.add(sq4);
        presetKeys.add(sq5);
    }

    public static void addKey(String key) {
        presetKeys.add(key);
    }




}