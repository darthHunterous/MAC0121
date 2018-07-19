/******************************************************************************
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Non-overlapping interval search
 * Data: 02/11/2017
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

public class LocateBST {
    public static void main(String[] args) {
        BST<Integer, Integer> tree = new BST<Integer, Integer>();
        In intervals = new In(args[0]); // le os intervalos a partir de um arquivo
        int[] integers = StdIn.readAllInts(); /* le os inteiros a serem procurados
        a partir da entrada padrao */

        // constroi a arvore binaria
        while (!intervals.isEmpty())
            tree.put(intervals.readInt(), intervals.readInt());

        // procura os elementos na arvore binaria
        for (int i = 0; i < integers.length; i++) {
            StdOut.print(integers[i] + ": ");
            if (tree.floor(integers[i]) == null) /* caso em que o inteiro e' menor
            que o menor intervalo da arvore */
                StdOut.println("left of [" + tree.min() + ", " + tree.get(tree.min()) + "]");
            else
                if (tree.ceiling(integers[i]) == null && tree.get(tree.floor(integers[i])) < integers[i])
                // caso em que o inteiro e' maior que o maior intervalo da arvore
                    StdOut.println("right of [" + tree.max() + ", " + tree.get(tree.max()) + "]");
                else
                    if (tree.get(tree.floor(integers[i])) >= integers[i]) {
                    // caso em que o inteiro esta contido em um intervalo
                        StdOut.print("[" + tree.floor(integers[i]) + ", ");
                        StdOut.println(tree.get(tree.floor(integers[i])) + "]");
                    }
                    else { /* caso as verificacoes cheguem ate aqui, o inteiro
                    esta contido entre dois intervalos */
                        StdOut.print("between [" + tree.floor(integers[i]) + ", ");
                        StdOut.print(tree.get(tree.floor(integers[i])) + "] & [");
                        StdOut.print(tree.ceiling(integers[i]) + ", ");
                        StdOut.println(tree.get(tree.ceiling(integers[i])) + "]");
                    }
        }
    }
}
