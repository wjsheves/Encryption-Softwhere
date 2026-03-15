import java.util.ArrayList;
import java.util.List;

public class Data {

    static String hashToCheck = "9bd0bb257f4badbc4dc9930e3e20ca82";
    static Boolean devModeEnabled = false;
    static Boolean typeChoseD = false;
    static Boolean typeChoseE = false;

    static String sq1 = "{[e|rm/2/4/1|]}";
    static String sq2 = "{[e|rm/4/1/1|]}";
    static String sq3 = "{[e|rm/1/2/1|]}";
    static String sq4 = "{[e|rm/4/3/1|]}";

    public static List<String> keys = new ArrayList<>();

    static {
        keys.add(sq1);
        keys.add(sq2);
        keys.add(sq3);
        keys.add(sq4);
    }

    public static void addKey(String key) {
        keys.add(key);
    }




}