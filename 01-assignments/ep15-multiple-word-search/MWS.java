import java.util.*;
//import java.util.regex.Pattern;

public class MWS {
    public static boolean allFound(int[] array) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == 0)
                return false;
        return true;
    }

    public static boolean removeTop(String[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++)
            if (array[i].equals(array[start]))
                return true;
        return false;
    }

    public static void main(String[] args) {
        In text = new In(args[0]);
        In query = new In(args[1]);
        boolean verbose = false;

        if (args.length > 2)
            verbose = true;

        String wholeText = text.readAll();
        String[] fullText = wholeText.split("\\b");
        String[] queries = query.readAllStrings();
        Arrays.sort(queries);

        int[] queriesCount = new int[queries.length];
        for (int i = 0; i < queriesCount.length; i++)
            queriesCount[i] = 0;
        boolean completedQueries = false;

        int currentStart = -1;
        int currentEnd = -1;
        LinkedList<Integer> nextStart = new LinkedList<Integer>();

        int minimumStart = -1;
        int minimumEnd = -1;

        int minimumLength = fullText.length + 1;
        int currentLength = 0;
        boolean topRepeated = false;

        for (int i = 0; i < fullText.length; i++) {
            int binIndex = Arrays.binarySearch(queries, fullText[i]);
            if (binIndex >= 0) {
                if (currentStart == -1)
                    currentStart = i;
                else {
                    currentEnd = i;
                    nextStart.add(i);
                }
                fullText[i] = "*" + fullText[i] + "*";
                queriesCount[binIndex] += 1;
                if (!completedQueries && allFound(queriesCount))
                    completedQueries = true;
            }
            if (completedQueries)
                topRepeated = removeTop(fullText, currentStart, currentEnd);
            if (fullText[i].matches("[A-z0-9À-ÿ*]+"))
                currentLength++;
            else
                StdOut.println(fullText[i]);
            if (completedQueries && !topRepeated && currentLength < minimumLength) {
                minimumLength = currentLength;
                minimumStart = currentStart;
                minimumEnd = currentEnd;
            }
            if (topRepeated) {
                currentStart = nextStart.getFirst();
                nextStart.remove();
            }
        }
        StdOut.println(minimumStart + " " + minimumEnd);
        StdOut.println("Number of words in interval: " + minimumLength);
        if(verbose)
            for (int i = minimumStart; i <= minimumEnd; i++)
                StdOut.print(fullText[i]);
    }
}
//https://stackoverflow.com/questions/2734313/google-search-results-how-to-find-the-minimum-window-that-contains-all-the-sear
