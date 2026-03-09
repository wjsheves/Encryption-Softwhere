import java.util.Scanner;


class Questions {

    static String questionsAsk(String questionnumber) {

        if (questionnumber.equals("1")){

            Questions.askQuestionOne();
        }
        else if (questionnumber.equals("2")){
            Questions.askQuestionTwo();
        }
        return "";
    }

    public static boolean checkDeveloperMode() {

        Scanner scanner = new Scanner(System.in);
        Util.print("Dev mode?");
        String eord = scanner.nextLine();

        if (eord.equals(Data.hashToCheck)) {
            Util.print("Developer Mode Indicated :)");
            Util.print("Welcome Mr. Stark");
            Data.devModeEnabled = true;
        } else {
            Util.print("Incorrect Password!");
            Util.print("Terminating Login!");
        }

        return Data.devModeEnabled;

    }

    static void askQuestionOne() {

        Scanner scanner = new Scanner(System.in);
        Util.print("Would you like to Encrypt or Decrypt, Type 1 or 2:");
        String eord = scanner.nextLine();
        String holder = "";

        if (eord.equals("1")) {
            Util.print("User chose 'Encrypt'");
            Data.typeChoseE = true;
            Data.typeChoseD = false;
        } else if (eord.equals("2")) {
            Util.print("User chose 'Decrypt'");
            Data.typeChoseD = true;
            Data.typeChoseE = false;
        } else {
            Util.print("Not Valid Entry!");
            askQuestionOne();
        }

        askQuestionTwo();
    }

    static void askQuestionTwo() {


        Scanner scanner = new Scanner(System.in);
        Util.print("Would you like to make an " + Util.getType() + "ion key or chose a preset **Note Dev Mode must be enabled for you to chose a preset**, Type 1 or 2:");
        String eord = scanner.nextLine();

        if (eord.equals("1")) {
            Util.print("User chose 'Make " + Util.getType() + "ion Key'");
            Util.print("Input " + Util.getType() + "ion Key:");
            String uio = scanner.nextLine();
            Util.makeKey(uio);
            askQuestionThree();
        } else if (eord.equals("2")) {
            Util.print("User chose 'Choose " + Util.getType() + "ion Key'");
            askPreset();
            askQuestionThree();
        } else {
            Util.print("Not Valid Entry!");
            askQuestionTwo();
        }

    }

    static void askQuestionThree() {
        Util.print("What would you like to " + Util.getType()+ "?");
        Util.getString();
    }


    static void askPreset() {

        if (Data.devModeEnabled.booleanValue()) {
            
            String selectedSequence;

            Scanner scanner = new Scanner(System.in);
            Util.print("Here are the following presets:");
            Util.print("1. " + Data.sq1);
            Util.print("2. " + Data.sq2);
            Util.print("3. " + Data.sq3);
            Util.print("4. " + Data.sq4);
            String eord = scanner.nextLine();

            if (eord.equals("1")) {
                Util.print("User chose '" + Data.sq1 + "'");
                selectedSequence = Data.sq1;
            } else if (eord.equals("2")) {
                Util.print("User chose '" + Data.sq2 + "'");
                selectedSequence = Data.sq2;
            } else if (eord.equals("3")) {
                Util.print("User chose '" + Data.sq3 + "'");
                selectedSequence = Data.sq3;
            } else if (eord.equals("4")) {
                Util.print("User chose '" + Data.sq4 + "'");
                selectedSequence = Data.sq4;
            } else {
                Util.print("Not Valid Entry!");
                askPreset();
            }

        }else {
            Util.print("Not Valid Mode!");
            Scanner scanner = new Scanner(System.in);
            Util.print("Would you like to activate Dev Mode? y/n");
            String eord = scanner.nextLine();

            if (eord.equals("y")) {
                checkDeveloperMode();
            }else if (eord.equals("n")) {

            }else {

            }

        }
    }

    public static void main(String[] args) {


    }
}