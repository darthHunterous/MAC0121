/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Variante do Web Exercise 2.5.13 (Sorting with many duplicates)
 * Data: 01/10/2017
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

/* Esta classe usa um 3-way Quicksort para lidar bem com um vetor repleto
de duplicatas. A razao pela qual a cota O(n log t) vale para o pior caso
(quando o vetor esta ordenado) se da pela utilizacao do metodo shuffle de
StdRandom. Com isso, quando a entrada e' uma string ordenada, ela e'
embaralhada antes de se realizar a ordenacao, o que e' responsavel por aumentar
a eficiencia */
public class DuplicateSort {

    /* Ordena o vetor passado como parametro de maneira crescente, de
    acordo com a ordem natural */
    public static void sort(Comparable[] array) {
        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }

    /* Ordena o subarray de array[begin] a array[end] */
    private static void sort(Comparable[] array, int begin, int end) {
        if (end <= begin) return;
        int equalsBegin = begin;
        int equalsEnd = end;
        Comparable referenceObject = array[begin];

        int i = begin;
        while (i <= equalsEnd) {
            int compare = array[i].compareTo(referenceObject);
            if (compare < 0)
                exchange(array, equalsBegin++, i++);
            else
                if (compare > 0)
                    exchange(array, i, equalsEnd--);
                else
                    i++;
        }

        sort(array, begin, equalsBegin - 1);
        sort(array, equalsEnd + 1, end);
    }

    /* Troca o elemento na posicao i do array com o elemento na posicao j */
    private static void exchange(Comparable[] array, int i, int j) {
        Comparable aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }

}
