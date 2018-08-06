import java.util.Arrays;

public class UnionOfIntervalsTimed {
    public static void main (String[] args) {
        In randomInput;
        double[] allPoints;
        boolean verboso;
        Interval1D[] intervals, unitedIntervals;
        int i, j, k;
        double auxMax, totalLength;

        long startTime = System.currentTimeMillis();

        randomInput = new In();
        allPoints = randomInput.readAllDoubles();
        intervals = new Interval1D[allPoints.length/2];
        unitedIntervals = new Interval1D[intervals.length];
        totalLength = 0;

        if (allPoints.length % 2 != 0) {
            StdOut.println("Invalid intervals entries");
            return;
        }

        if (args.length > 0)
            verboso = true;
        else
            verboso = false;

        for (i = 0, j = 0; i < allPoints.length; i += 2, j++)
            intervals[j] = new Interval1D(allPoints[i], allPoints[i+1]);

        Arrays.sort(intervals, Interval1D.MIN_ENDPOINT_ORDER);

        for (i = 0, k = 0, j = 1; i < intervals.length; i += j, k++) {
            for (j = 1, auxMax = intervals[i].max(); (i+j) < intervals.length && intervals[i+j].min() <= auxMax; j++)
                if (intervals[i+j].max() > auxMax)
                    auxMax = intervals[i+j].max();
            unitedIntervals[k] = new Interval1D(intervals[i].min(), auxMax);
            totalLength += unitedIntervals[k].length();
        }

        StdOut.println("Total length: " + totalLength + " [" + k);
        if (k == 1)
            StdOut.println(" component]");
        else
            StdOut.println(" components]");

        if (k > 60)
            verboso = false;

        for (i = 0; verboso && unitedIntervals[i] != null && i < unitedIntervals.length; i++)
            StdOut.println(unitedIntervals[i].toString());

        long endTime = System.currentTimeMillis();

        StdOut.println(endTime - startTime);
    }
}
