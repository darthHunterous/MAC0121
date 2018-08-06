/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Segmento comum mais longo e subpalíndromo mais longo
 * Data: 20/09/2017
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

public class LongestPalindrome {
    public static void main (String[] args) {

        // implementacao analoga a LongestCommonSegment porem inverte a string de entrada com StringBuilder para encontrar palindromos
        String s;

        if (args.length > 0) {
            In sIn = new In (args[0]);
            s = sIn.readAll();
        }
        else
            s = StdIn.readAll();

        s = s.replaceAll("\\s+", " ").trim();

        StringBuilder toRevert = new StringBuilder(s);
        String t = toRevert.reverse().toString();

        SuffixArray2 texts = new SuffixArray2(s, t);

        int longestCommonSegmentLength = 0;
        String longestCommonSegment = "";
        int suffixArrayLength = texts.length();

        for (int i = 1; i < suffixArrayLength; i++) {
            int aux = texts.lcp(i);
            if (aux > longestCommonSegmentLength) {
                longestCommonSegmentLength = aux;
                longestCommonSegment = texts.select(i);
            }
        }

        StdOut.println(longestCommonSegment.substring(0, longestCommonSegmentLength));
    }
}
