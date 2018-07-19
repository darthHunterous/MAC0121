import pieces.*;
import java.util.Arrays;

public class Match {

    public static void match (NutsAndBolts collection) {
        Nut zero = new Nut(0);
        int[] toSortDimension = new int[collection.N];

        for (int i = 0; i < toSortDimension.length; i++)
            toSortDimension[i] = collection.bolts[i].compareTo(zero);
        Arrays.sort(toSortDimension);

        for (int i = 0; i < toSortDimension.length; i++) {
            collection.bolts[i] = new Bolt(toSortDimension[i]);
            collection.nuts[i] = new Nut(toSortDimension[i]);
        }


    }
}
