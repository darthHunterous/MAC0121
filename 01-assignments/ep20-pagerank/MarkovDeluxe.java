/******************************************************************************
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Pagerank - Parte 1
 * Data: 14/12/2017
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

/* DEPEDENDENCIAS: (Todos de Algs4)
-Bag.java
-Digraph.java
-SparseMatrix.java
-SparseVector.java
-ST.java
-Stack.java
-SymbolDigraph.java
*/
public class MarkovDeluxe {
    public static void main(String[] args) {
        In dataFile = new In(args[0]); // Arquivo do qual sera lido o grafo dirigido
        String separator = args[1]; // Caracter usado como separador
        double linkProbability = Double.parseDouble(args[2]); /* Probabilidade de que
        o navegante escolha um link ao acaso na pagina */
        int iterations = Integer.parseInt(args[3]); /* Numero de iteracoes (saltos
        de pagina) */
        boolean testConvergence = false; // Mais uma iteracao para verificar convergencia
        if (args.length > 4)
            testConvergence = true;

        // Inicializa o grafo dirigido
        SymbolDigraph pages = new SymbolDigraph(args[0], separator);
        Digraph directedGraph = pages.digraph();

        // Construcao da matriz esparsa que identifica os links de uma pagina a outra
        int N = directedGraph.V();
        SparseMatrix transition = new SparseMatrix(N);
        int size;
        double aux;

        // isDangle identifica quais vertices nao possuem links para outros
        SparseVector isDangle = new SparseVector(N);
        for (int v = 0; v < N; v++) {
            size = directedGraph.outdegree(v);
            if (size == 0)
                isDangle.put(v, 1.0);
            for (int w : directedGraph.adj(v)) {
                aux = transition.get(v, w);
                transition.put(v, w, aux + linkProbability/size);
            }
        }

        // Define a probabilidade de se saltar aleatoriamente para uma pagina
        double standardProb = (1.0 - linkProbability)/N;
        SparseVector matrixA = new SparseVector(N);
        for (int i = 0; i < N; i++) {
            if (isDangle.get(i) == 1)
                matrixA.put(i, linkProbability/N);
        }

        /* Define o vetor identidade resultante e aplica o power method
        para obter o pagerank */
        SparseVector standardProbIdentity = new SparseVector(N);
        for (int i = 0; i < N; i++)
            standardProbIdentity.put(i, standardProb);
        SparseVector rank = new SparseVector(N);
        SparseVector newRank = new SparseVector(N);
        rank.put(0, 1.0);
        SparseMatrix transposedTransition = transition.transpose();
        SparseVector factor1 = new SparseVector(N);
        for (int i = 0; i <= iterations; i++) {
            aux = matrixA.dot(rank);
            for (int j = 0; j < N; j++){
                factor1.put(j, aux);
            }
            newRank = transposedTransition.times(rank).plus(standardProbIdentity);
            newRank = newRank.plus(factor1);
            if (i != iterations)
                rank = newRank;
        }

        // Imprime os vertices em ordem alfabetica com seu Pagerank
        for (String v : pages.vertexNames()) {
            StdOut.printf("%10.9f ", rank.get(pages.indexOf(v)));
            if (testConvergence) {
                StdOut.printf("%10.9f ", newRank.get(pages.indexOf(v)));
                StdOut.print(directedGraph.indegree(pages.indexOf(v)) + " ");
            }
            StdOut.println(v);
        }
    }
}
