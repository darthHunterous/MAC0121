public class FileCompare {
    public static void main (String[] args) {

        In file1 = new In(args[0]);
        In file2 = new In(args[1]);

        String[] txt1 = file1.readAllStrings();
        String[] txt2 = file2.readAllStrings();

        Boolean errou = false;

        if (txt1.length != txt2.length)
            errou = true;

        for(int i = 0; i < txt1.length; i++)
            if (!txt1[i].equals(txt2[i])) {
                errou = true;
                StdOut.println(txt1[i]);
                break;
            }

        StdOut.println(errou);
    }
}
