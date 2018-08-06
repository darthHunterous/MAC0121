public class ArrayPrinter {
    public static void main (String[] args) {
        long startTime = System.currentTimeMillis();

        In randomInput = new In();
        double[] randomDoubles = randomInput.readAllDoubles();

        for (int i = 0; i < randomDoubles.length; i++)
            randomDoubles[i] = 0;

        long endTime = System.currentTimeMillis();

        StdOut.println(endTime - startTime);
    }
}
