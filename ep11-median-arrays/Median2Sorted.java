/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Web Exercise 2.5.23 (Median given two sorted arrays)
 * Data: 05/10/2017
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

public class Median2Sorted {

    /* O algoritmo abaixo apresenta complexidade de tempo de pior caso O(log N) pois utiliza um metodo de divisao e conquista
    para reduzir os vetores de Comparables a metade em cada passo recursivo da funcao, com isso ha ganho na eficiencia e evita-se
    o merge dos dois vetores ordenados a e b, o que implicaria em um tempo linear de execucao */
    private static Comparable kPosition(Comparable[] a, Comparable[] b, int aBegin, int aEnd, int bBegin, int bEnd, int k) {

        if (aBegin == aEnd)
            return b[bBegin + k];
        if (bBegin == bEnd)
            return a[aBegin + k];

        int aMiddleElement = (aEnd - aBegin) / 2;
        int bMiddleElement = (bEnd - bBegin) / 2;

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
}
