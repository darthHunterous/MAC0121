/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Número de ocorrências em lista ordenada
 * Data: 18/09/2017
 *
 * Baseado em referências de aula
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class CountOccurrences {

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
    }
}
