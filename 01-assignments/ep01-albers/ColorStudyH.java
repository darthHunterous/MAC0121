/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Variante do Creative Ex. 3.1.33 (Color study)
 * Data: 17/08/2017
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
import java.awt.Color;

public class ColorStudyH {
    public static void main(String[] args) {
        int i, j, colorSmall, SIZE;
        float h1, h2, distanceFactor;
        Color smallAlbers, bigAlbers;

        StdDraw.setXscale(-1, 17);
        StdDraw.setYscale(-1, 17);
        StdDraw.setPenRadius(0.05);

        h1 = Float.parseFloat(args[0]);
        h2 = Float.parseFloat(args[1]);

        SIZE = 16;

        colorSmall = SIZE - 1;

        for(i = 0; i < SIZE; i++) {
            for (j = 0; j < SIZE; j++) {

                distanceFactor = (float)(Math.sqrt(i*i + j*j)/Math.sqrt(2*(SIZE-1)*(SIZE-1)));
                bigAlbers = Color.getHSBColor(distanceFactor*(h2-h1)+h1, 0.95f, 0.95f);
                StdDraw.setPenColor(bigAlbers);
                StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);

                smallAlbers = new Color(colorSmall, colorSmall, colorSmall);
                StdDraw.setPenColor(smallAlbers);
                StdDraw.filledSquare(i + 0.5, j + 0.5, 0.25);

                colorSmall--;
            }
            colorSmall += (2 * SIZE - 1);
        }

        StdDraw.save("study-" + h1 + "-" + h2 + ".png");
    }
}
