/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Web Exercise 4.2.1 (Union of intervals)
 * Data: 10/09/2017
 *
 * Baseado em nas referências de aula
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

import java.util.Arrays;
import java.awt.Color;

public class UnionOfIntervals {
    public static void main (String[] args) {
        In randomInput;
        double[] allPoints;
        boolean verboso;
        Interval1D[] intervals, unitedIntervals;
        int i, j, k;
        double auxMax, totalLength, segmentHeight;

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

        if (intervals.length > 60)
            verboso = false;

        for (i = 0, segmentHeight = 0.1; verboso && i < intervals.length; i++, segmentHeight += 0.01) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.005);
            StdDraw.line(intervals[i].min(), segmentHeight, intervals[i].max(), segmentHeight);
            StdDraw.setPenRadius(0.001);
            StdDraw.line(intervals[i].min(), 0, intervals[i].min(), 1);
            StdDraw.line(intervals[i].max(), 0, intervals[i].max(), 1);
        }

        segmentHeight += 0.05;
        for (i = 0; verboso && unitedIntervals[i] != null && i < unitedIntervals.length; i++, segmentHeight += 0.01) {
            StdOut.println(unitedIntervals[i].toString());
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.005);
            StdDraw.line(unitedIntervals[i].min(), segmentHeight, unitedIntervals[i].max(), segmentHeight);
        }
    }
}
