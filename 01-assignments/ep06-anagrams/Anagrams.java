/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Web Exercise 4.2.12 (Anagrams)
 * Data: 14/09/2017
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

import java.util.Arrays;
import java.util.Comparator;

public class Anagrams {

    public static final Comparator<Anagrams> ALPHABETICAL_ORDER = new AlphabeticalComparator();

    private final String word;
    private final int position;

    public Anagrams(String word, int position) {
        this.word = word;
        this.position = position;
    }

    private static class AlphabeticalComparator implements Comparator<Anagrams> {
        public int compare(Anagrams a, Anagrams b) {
            return a.word.compareTo(b.word);
        }
    }

    public static void main(String[] args) {
        String[] wordList = StdIn.readAllStrings();
        Anagrams[] sortedList = new Anagrams[wordList.length];

        for (int i = 0; i < wordList.length; i++) {
            char[] auxCharArray = wordList[i].toLowerCase().toCharArray();
            Arrays.sort(auxCharArray);
            String auxString = new String(auxCharArray);
            sortedList[i] = new Anagrams(auxString, i);
        }

        Arrays.sort(sortedList, Anagrams.ALPHABETICAL_ORDER);

        for (int i = 0; i < sortedList.length; i++)
            if ((i+1 < sortedList.length) && sortedList[i].word.equals(sortedList[i+1].word)) {
                StdOut.print("* " + wordList[sortedList[i].position]);
                StdOut.print(" " + wordList[sortedList[i+1].position]);
                for (i++; i < sortedList.length && sortedList[i].word.equals(sortedList[i+1].word); i++)
                    StdOut.print(" " + wordList[sortedList[i+1].position]);
                StdOut.println();
            }
    }
}
