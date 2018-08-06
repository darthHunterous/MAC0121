import java.util.Arrays;
import java.util.Comparator;

public class AnagramsTimed {

    public static final Comparator<AnagramsTimed> ALPHABETICAL_ORDER = new alphabeticalComparator();

    private final String word;
    private final int position;

    public AnagramsTimed(String word, int position){
        this.word = word;
        this.position = position;
    }

    private static class alphabeticalComparator implements Comparator<AnagramsTimed> {
        public int compare(AnagramsTimed a, AnagramsTimed b) {
            return a.word.compareTo(b.word);
        }
    }

    public static void main (String[] args) {
        long startTime = System.currentTimeMillis();
        String[] wordList = StdIn.readAllStrings();
        AnagramsTimed[] sortedList = new AnagramsTimed[wordList.length];

        for (int i = 0; i < wordList.length; i++) {
            char[] auxCharArray = wordList[i].toLowerCase().toCharArray();
            Arrays.sort(auxCharArray);
            String auxString = new String(auxCharArray);
            sortedList[i] = new AnagramsTimed(auxString, i);
        }

        Arrays.sort(sortedList, AnagramsTimed.ALPHABETICAL_ORDER);

        for (int i = 0; i < sortedList.length; i++)
            if ((i+1 < sortedList.length) && sortedList[i].word.equals(sortedList[i+1].word)) {
                StdOut.print("* " + wordList[sortedList[i].position]);
                StdOut.print(" " + wordList[sortedList[i+1].position]);
                for(i++; i < sortedList.length && sortedList[i].word.equals(sortedList[i+1].word); i++)
                    StdOut.print(" " + wordList[sortedList[i+1].position]);
                StdOut.println();
            }
        long finishTime = System.currentTimeMillis();

        StdOut.println(finishTime - startTime);
    }
}
