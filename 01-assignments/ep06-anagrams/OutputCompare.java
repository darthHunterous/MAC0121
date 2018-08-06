public class OutputCompare {
    public static void main (String[] args) {
        In myOutput = new In (args[0]);
        In yoshiOutput = new In (args[1]);

        String[] myArray = myOutput.readAllStrings();
        String[] yoshiArray = yoshiOutput.readAllStrings();

        if (myArray.length != yoshiArray.length) {
            StdOut.println("O tamanho dos arrays nao eh igual");
            return;
        }

        for (int i = 0; i < myArray.length; i++) {
            if (!myArray[i].equals(yoshiArray[i])) {
                StdOut.println("Nao sao iguais");
                return;
            }
        }
        StdOut.println("Sao iguais");
    }
}
