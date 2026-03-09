import java.util.Scanner;

public class Util {

    public static String cleaner(String I_Unclean) {

        String rfBlocking = I_Unclean.replace("{[","");
        String rbBlocking = rfBlocking.replace("]}","");


        String clean = rbBlocking;

        return clean;
    }

    public static String makeKey(String iKey) {
        String type = "";
        String rtype = "";

        String slightlyCleanKey = Util.cleaner(iKey);
        if (slightlyCleanKey.contains("e|")) {
            type = "encrypt";
            rtype = "e|";

        } else if (slightlyCleanKey.contains("d|")) {
            type = "decrypt";
            rtype = "d|";
        }
        String slightlyCleanerKey = slightlyCleanKey.replace(rtype, "");
        print(type);
        print(slightlyCleanerKey);

        return "";

    }

    static String print(String input) {

        System.out.println(input);

        return input;
    }

    public static String getType() {
        String type = "";

        if (Data.typeChoseD) {
            type = "Decrypt";
        } else if (Data.typeChoseE) {
            type = "Encrypt";
        }

        return type;
    }

    public static String getString() {
        Scanner scanner = new Scanner(System.in);
        String userString = scanner.nextLine();

        return userString;
    }
}
