public class CountOccurrencesTimed {

    public static int binarySearch (int[] array, int x, int left, int right) {

        if (right <= left) return -1;
        int middle = left + (right - left) / 2;
        if (array[middle] > x)
            return binarySearch(array, x, left, middle);
        else if (array[middle] < x)
            return binarySearch(array, x, middle+1, right);
        else
            return middle;

    }

    public static void main (String[] args) {
        long startTime = System.currentTimeMillis();
        In unsortedList = new In(args[0]);
        int[] sortedArray = StdIn.readAllInts();
        int[] unsortedArray = unsortedList.readAllInts();

        for (int i = 0; i < unsortedArray.length; i++) {
            int index =  binarySearch(sortedArray, unsortedArray[i], 0, sortedArray.length);
            if (index == -1)
                StdOut.println("0");
            else {
                int occurrences;
                int j;
                for (occurrences = 1, j = index+1; j < sortedArray.length && sortedArray[j] == unsortedArray[i]; occurrences++, j++);
                for (j = index-1; j >= 0 && sortedArray[j] == unsortedArray[i]; occurrences++, j--);
                StdOut.println(occurrences);
            }
        }
        long endTime = System.currentTimeMillis();
        StdOut.println(endTime - startTime);
    }
}
