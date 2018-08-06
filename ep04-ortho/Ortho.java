public class Ortho {

    public static String[] stringPossibilities (String s) {
        String[] possibilities = {s, s.toLowerCase()};

        return possibilities;
    }

    public static int binarySearch (String s, String[] sArray, int min, int max) {
        if (max <= min) return -1;
        int middle = min + (max - min)/2;
        int comp = sArray[middle].compareTo(s);
        if (comp > 0)
          return binarySearch (s, sArray, min, middle);
        else if(comp < 0) return binarySearch (s, sArray, middle + 1, max);
        else return middle;
    }

    public static void main(String[] args) {
        In dictionaryFile = new In(args[0]), textFile = new In();
        int i, j, k = 0, found = -1;
        boolean marked = false, allContained = false, noMarkYet = true;
        String alphabeticOnlyText, wholeText;
        String[] dictionary = dictionaryFile.readAllStrings(), alphabeticOnly;
        StringBuilder mountTextAlphabetic = new StringBuilder();
        StringBuilder mountTextFull = new StringBuilder();

        char temp;
        String[] aux = new String[2];

        if (args.length == 2 && args[1].equals("-m"))
            marked = true;

        while (textFile.hasNextChar()) {
            temp = textFile.readChar();
            mountTextFull.append(temp);
            if (!Character.isAlphabetic(temp))
                temp = ' ';
            mountTextAlphabetic.append(temp);
        }

        wholeText = mountTextFull.toString();

        alphabeticOnlyText = mountTextAlphabetic.toString();
        alphabeticOnly = alphabeticOnlyText.split(" ");

        for (i = 0; i < alphabeticOnly.length && allContained && marked; i++, found = -1) {
            while (alphabeticOnly[i].equals(""))
                i++;
            aux = stringPossibilities(alphabeticOnly[i]);
            for (j = 0; j < 2 && found == -1; j++)
                found = binarySearch (aux[j], dictionary, 0, dictionary.length);
            if (found == -1) {
                allContained = false;
                break;
            }
        }

        /*if (allContained)
            StdOut.println(wholeText);*/

        for (i = 0; i < alphabeticOnly.length && !allContained; i++, found = -1) {
            aux = stringPossibilities(alphabeticOnly[i]);
            for (j = 0; j < 2 && found == -1; j++)
                found = binarySearch (aux[j], dictionary, 0, dictionary.length);
            if(found == -1 && alphabeticOnly[i].length() >= 1 && !marked)
                StdOut.println(alphabeticOnly[i]);
            if(found == -1 && alphabeticOnly[i].length() >= 1 && marked) {
                    int init = mountTextFull.lastIndexOf('*' + alphabeticOnly[i]);
                    if (init == -1) {
                        found = mountTextFull.indexOf(alphabeticOnly[i]);
                        if (found != -1) {
                            mountTextFull.insert(found, '*');
                            mountTextFull.insert(found + alphabeticOnly[i].length() + 1, '*');
                        }
                    }
                    else {
                        found = mountTextFull.indexOf(alphabeticOnly[i], init + 2);
                        mountTextFull.insert(found, '*');
                        mountTextFull.insert(found + alphabeticOnly[i].length() + 1, '*');
                    }
            }
        }

        if(!allContained && marked) {
            wholeText = mountTextFull.toString();
            StdOut.println(wholeText);
        }
    }
}
