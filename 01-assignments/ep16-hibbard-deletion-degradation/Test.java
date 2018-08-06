public class Test {
    public static void main (String[] args) {
        BST<Integer, Integer> tree = new BST<Integer, Integer>();
        int aux;

        while (!StdIn.isEmpty()) {
            aux = StdIn.readInt();
            tree.put(aux, aux);
        }

        StdOut.println(tree.ipl_ave());
    }
}
