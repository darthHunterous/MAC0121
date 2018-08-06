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

public class LongestCommonSegment {

    public static void main (String[] args) {

        String s;
        String t;

        // Cria novas entradas para o programa com base nos argumentos passados pela linha de comando
        if (args.length > 0) {
            In sIn = new In (args[0]);
            In tIn = new In (args[1]);
            s = sIn.readAll();
            t = tIn.readAll();
            s = s.replaceAll("\\s+", " ").trim(); // remove multiplos espacos em branco e quebras de linha
            t = t.replaceAll("\\s+", " ").trim();
        }
        else { // Le da entrada padrao caso nao haja argumentos na linha de comando
            s = StdIn.readString();
            t = StdIn.readString();
        }

        // cria um array de sufixos de maneira eficiente em relacao a memoria do computador
        SuffixArray2 texts = new SuffixArray2(s, t);

        int longestCommonSegmentLength = 0;
        String longestCommonSegment = "";
        int suffixArrayLength = texts.length();

        // encontra o segmento comum mais longo com base na classe SuffixArray2
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
