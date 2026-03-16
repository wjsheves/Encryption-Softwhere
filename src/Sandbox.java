public class Sandbox {

    public static Long getNumericReferenceNumber(String str) {

        String result = "";

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (Character.isLetter(ch)) {
                char initialCharacter = Character.isUpperCase(ch) ? 'A' : 'a';
                result = result.concat(String.valueOf((ch - initialCharacter + 1)));
            } else result = result + ch;
        }

        return Long.parseLong(result);
    }

    public static void main(String[] args) {

        Util.print(getNumericReferenceNumber("abc").toString());
}
}
