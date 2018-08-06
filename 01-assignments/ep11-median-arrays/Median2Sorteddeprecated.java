import java.util.Arrays;

public class Median2Sorteddeprecated {

    private static Comparable kPosition(Comparable[] a, Comparable[] b, int aBegin, int aEnd, int bBegin, int bEnd, int k) {
        //StdOut.printf("Array1 [%d, %d]      Array2 [%d, %d]\n", aBegin, aEnd, bBegin, bEnd);

        if (aBegin == aEnd)
            return b[bBegin + k];
        if (bBegin == bEnd)
            return a[aBegin + k];

        int aMiddleElement = (aEnd - aBegin) / 2;
        int bMiddleElement = (bEnd - bBegin) / 2;

        /*int aMiddleElement = aBegin + (aEnd - aBegin)/2;
        int bMiddleElement = bBegin + (bEnd - bBegin)/2;

        if ((aEnd - aBegin) % 2 != 0)
            aMiddleElement++;
        if ((bEnd - bBegin) % 2 != 0)
            bMiddleElement++;*/


        //StdOut.printf("        aMiddleElement == %d && bMiddleElement == %d && k == %d\n", aMiddleElement, bMiddleElement, k);

        /*
        if (aMiddleElement + bMiddleElement >= k) {
            if (a[aMiddleElement].compareTo(b[bMiddleElement]) >= 1)
                return kPosition(a, b, aBegin, aEnd + aMiddleElement, bBegin, bEnd, k); //aEnd pode virar por aEnd + aMiddleElement
            else
                return kPosition(a, b, aBegin, aEnd, bBegin, bEnd + bMiddleElement, k); //analogo a aEnd com bEnd aqui
        }
        else {
            if (a[aMiddleElement].compareTo(b[bMiddleElement]) >= 1)
                return kPosition(a, b, aBegin, aEnd, bBegin + bMiddleElement + 1, bEnd, k - bMiddleElement - 1);
            else
                return kPosition(a, b, aBegin + aMiddleElement + 1, aEnd, bBegin, bEnd, k - aMiddleElement - 1);
        }*/
        if (aMiddleElement + bMiddleElement < k) {
            if (a[aBegin + aMiddleElement].compareTo(b[bBegin + bMiddleElement]) >= 1)
                return kPosition(a, b, aBegin, aEnd, bBegin + bMiddleElement + 1, bEnd, k - bMiddleElement - 1);
            else
                return kPosition(a, b, aBegin + aMiddleElement + 1, aEnd, bBegin, bEnd, k - aMiddleElement - 1);
        }
        else {
            if (a[aBegin + aMiddleElement].compareTo(b[bBegin + bMiddleElement]) >= 1)
                return kPosition(a, b, aBegin, aBegin + aMiddleElement, bBegin, bEnd, k);
            else
                return kPosition(a, b, aBegin, aEnd, bBegin, bBegin +  bMiddleElement, k);
        }
    }

    public static Comparable select(Comparable[] a, Comparable[] b, int k) {
        return kPosition(a, b, 0, a.length, 0, b.length, k);
    }

    public static void main(String[] args) {
        Integer[] array = {2, 3, 6, 7, 9};
        Integer[] array2 = {1, 4, 8, 10};

        StdOut.println(Arrays.toString(array));
        StdOut.println(Arrays.toString(array2));

        //StdOut.println(select(array, array2, 4));
        for (int i = 0; i < array.length + array2.length; i++)
            StdOut.printf("c[%d] == %d\n", i, select(array, array2, i));
    }
}
