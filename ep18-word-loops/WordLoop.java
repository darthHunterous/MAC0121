public class WordLoop {

    public static boolean differByOne(String a, String b) {
        int count = 0;
        for (int i = 0; i < b.length(); i++)
            if (a.charAt(i) != b.charAt(i)) count++;

        if (count == 1)
            return true;
        return false;
    }

    public static void main (String[] args) {
        In wordsIn = new In(args[0]);

        String[] wordPool = new String[100000];
        Graph graph = new Graph();

        int actualSize = 0;

        while(!wordsIn.isEmpty()) {
            String aux = wordsIn.readString();
            graph.addVertex(aux);
            wordPool[actualSize] = aux;
            actualSize++;
        }

        for (int i = 0; i < actualSize; i++)
            for (int j = i+1; j < actualSize; j++)
                if (differByOne(wordPool[i], wordPool[j]))
                    graph.addEdge(wordPool[i], wordPool[j]);
    }
}
