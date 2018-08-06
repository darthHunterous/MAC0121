public class OrthoMin {

    public static String onlyLetters (String s) {
        int i, length;
        char c;

        length = s.length();
        for (i = 0; i < length; i++) {
            c = s.charAt(i);
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')))
              break;
        }

        return s.substring(0, i);
    }

    public static String[] stringPossibilities (String s) {
        String[] possibilities = {s, s.toLowerCase()};

        return possibilities;
    }

    public static boolean binarySearch (String s, String[] sArray, int min, int max) {
        if (max <= min) return false;
        int middle = min + (max - min)/2;
        int comp = sArray[middle].compareTo(s);
        if (comp > 0)
          return binarySearch (s, sArray, min, middle);
        else if(comp < 0) return binarySearch (s, sArray, middle + 1, max);
        else return true;
    }

    public static void main(String[] args) {
        In dictionaryFile = new In(args[0]), textFile = new In();
        String[] dictionary = dictionaryFile.readAllStrings(), text;
        char temp;
        String[] aux = new String[3];
        int i, j;
        boolean marked = false, find = false;
        String aux2;
        StringBuilder mountText = new StringBuilder();

        while (textFile.hasNextChar()) {
            temp = textFile.readChar();
            if (!Character.isAlphabetic(temp))
                temp = ' ';
            mountText.append(temp);
        }

        aux2 = mountText.toString();

        text = aux2.split(" ")        ;

        if (args.length == 2 && args[1].equals("-m"))
            marked = true;

        for (i = 0; i < text.length; i++, find = false) {
            text[i] = onlyLetters(text[i]);
            aux = stringPossibilities(text[i]);
            for (j = 0; j < 2 && !find; j++)
              find = binarySearch (aux[j], dictionary, 0, dictionary.length);
            if(!find && text[i].length() >= 1)
              StdOut.println(text[i]);
        }
    }
}
