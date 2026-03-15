import java.util.Arrays;

public class Scrambler {
    public static String[] makeKey(String iKey) {
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
        String slightlyCleanererKey = slightlyCleanerKey.replace("|","");
        String eDType = slightlyCleanererKey.substring(0,3);
        String eDTypeC = eDType.replace("/","");
        String slightlyCleanerKey1 = slightlyCleanererKey.replace(eDType,"");
        String inners = slightlyCleanerKey1;
        String[] eSteps = inners.split("/");
        String[] eDData = {type,eDTypeC, Arrays.toString(eSteps)};
        Util.print(Arrays.toString(eDData));

        return eDData;

    }

    public static String Scramble(){


        return "";
    }
}