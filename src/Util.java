import java.util.Arrays;
import java.util.Scanner;

public class Util {

    public static String cleaner(String I_Unclean) {

        String rfBlocking = I_Unclean.replace("{[","");
        String rbBlocking = rfBlocking.replace("]}","");


        String clean = rbBlocking;

        return clean;
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