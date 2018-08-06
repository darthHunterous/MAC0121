public class Clairvoyant {
    public static void main (String[] args) {
        int cacheSize = Integer.parseInt(args[0]);
        boolean verbose = false;

        if (args.length > 1 && args[1].equals("-v"))
            verbose = true;

        int[] data = StdIn.readAllInts();

        IndexMaxPQ<Integer> indexedPriorityQueue = new IndexMaxPQ<Integer>(data.length);
        for (int i = 0; i < data.length; i++)
            indexedPriorityQueue.insert(data[i], i);

        for (int i : indexedPriorityQueue) {
            StdOut.println(i + " " + data[i]);
        }

    }
}
