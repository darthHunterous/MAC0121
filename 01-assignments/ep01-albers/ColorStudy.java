import java.awt.Color;

public class ColorStudy {
    public static void main(String[] args) {
        int i, j, color1, color2, SIZE;
        Color smallAlbers, bigAlbers;

        StdDraw.setXscale(-1, 17);
        StdDraw.setYscale(-1, 17);
        StdDraw.setPenRadius(0.05);

        SIZE = 16;

        color1 = SIZE - 1;
        color2 = SIZE - 1;

        //coordenadas comecam por baixo (color2/(SIZE * SIZE - 1))
        for(i = 0; i < SIZE; i++) { //i varia coluna
            for (j = 0; j < SIZE; j++) { //j varia linha

                bigAlbers = Color.getHSBColor(0.58f, color1/255f, 0.65f);
                StdDraw.setPenColor(bigAlbers);
                StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);

                smallAlbers = new Color(color2, color2, color2);
                StdDraw.setPenColor(smallAlbers);
                StdDraw.filledSquare(i + 0.5, j + 0.5, 0.25);

                color1 += SIZE;
                color2--;
            }
            color1 = SIZE -1 - i;
            color2 += (2 * SIZE - 1);
        } //consertei o smallAlbers, agora partir para o bigAlbers
    }
}
