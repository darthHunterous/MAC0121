/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Parafusos e Porcas
 * Data: 27/09/2017
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
