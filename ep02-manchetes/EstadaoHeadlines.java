/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Manchetes
 * Data: 21/08/2017
 *
 * Baseado nas referências de aula
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/


import org.apache.commons.lang3.StringEscapeUtils;

public class EstadaoHeadlines {
    public static void main (String[] args) {
        In headlines;
        String htmlSource, headlineLevel3;
        int i, beginH3, beginString, endString;

        headlines = new In(args[0]);
        htmlSource = headlines.readAll();

        beginH3 = 0;
        endString = 0;
        
        while (beginH3 != -1) {
            beginH3 = htmlSource.indexOf("<h3", endString);
            if(beginH3 != -1) {
              beginString = htmlSource.indexOf(">", beginH3) + 1;
              endString = htmlSource.indexOf("</h3", beginString);
              headlineLevel3 = htmlSource.substring(beginString, endString);
              StdOut.println(StringEscapeUtils.unescapeHtml4(headlineLevel3));
            }
        }
    }
}
