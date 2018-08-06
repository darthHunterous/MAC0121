/******************************************************************************
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Pagerank - Parte 2
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
 /* DEPENDENCIAS:
 -LinkFinder.java
 -Queue.java
 -SET.java
 -ST.java
 */
public class WikiGraph {
    private static void recycle(String v, Queue<String> q, ST<String, Integer> noTries) {
	    if (!noTries.contains(v)) {
	        noTries.put(v, 1);
	        q.enqueue(v);
	        return;
	    }
	    int triesSoFar = noTries.get(v);
	    if (triesSoFar < 20) { // HARD CODED!
	        noTries.put(v, triesSoFar + 1);
	        q.enqueue(v);
	    }
    }

    public static void main(String[] args) throws Exception {
        // timeout connection after 500 miliseconds
        System.setProperty("sun.net.client.defaultConnectTimeout", "500");
        System.setProperty("sun.net.client.defaultReadTimeout",    "2000");

        String s = args[0];  // initial web page for BFS
        String t = args[1];  // must contain t in webserver's name
        String contains = args[2]; // Chave a ser procurada na pagina
        int MAXN = Integer.parseInt(args[3]);
        Queue<String> q = new Queue<String>();
        q.enqueue(s);
	    SET<String> discovered = new SET<String>();
        discovered.add(s);
        SET<String> visited = new SET<String>();
	    ST<String, Integer> numberOfTries = new ST<String, Integer>();
        while (!q.isEmpty() && visited.size() < MAXN) {
            String v = q.dequeue();
	        if (visited.contains(v)) continue;
            String input = null;
            try {
                In in = new In(v);
                input = in.readAll();
            } catch (IllegalArgumentException e) {
                StdOut.println("[could not open " + v + "]");
		        recycle(v, q, numberOfTries);
                continue;
            }
	        visited.add(v);
	        StdOut.print(v + " ");
	        Queue<String> links = LinkFinder.links(input, v);
	        for (String w : links) {
		        if (LinkFinder.validLink(w, t) && !discovered.contains(w)) {
		            try {
			            In in = new In(w);
                        In aux = in;
                        String temp = aux.readAll();
                        if (temp.contains(contains)) // Checa se a pagina contem a chave
                            StdOut.print(w + " ");
		            } catch (Exception e) {
			            continue;
		            }
		            q.enqueue(w);
		            discovered.add(w);
		        }
                else if (visited.contains(w) || discovered.contains(w)) {
                    In aux = new In(w);
                    String temp = aux.readAll();
                    if (temp.contains(contains)) // Imprime a pagina se ela contem a chave
                        StdOut.print(w + " ");
                }
	        }
            StdOut.println();
	    }
    }
}
