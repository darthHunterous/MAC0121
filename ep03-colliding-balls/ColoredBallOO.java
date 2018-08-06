/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Variante do Web Exercise 1.5.39 (Elastic collisions)
 * Data: 28/08/2017
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

import java.awt.Color;

public class ColoredBallOO {

    private Vector position;
    private Vector velocity;
    private final double radius;
    private final Color color;

    public ColoredBallOO(Vector p, Vector v, double r, Color c) {
        position = p;
        velocity = v;
        radius = r;
        color = c;
    }

    public Vector pos() {
        return this.position;
    }

    public Vector vel() {
        return this.velocity;
    }

    public double radius() {
        return this.radius;
    }

    public void setVel(Vector v) {
        this.velocity = v;
    }

    public void updatePosition(double dt) {
        this.position = this.pos().plus(vel().scale(dt));
    }

    public void treatWalls(double size, double dt) {
        Vector currentPosition = new Vector(pos().length()), auxVector;
        currentPosition = pos();
        double[] auxVelocity1 = {vel().cartesian(0)*(-1), vel().cartesian(1)},
                 auxVelocity2 = {vel().cartesian(0), vel().cartesian(1)*(-1)};
        this.updatePosition(dt);
        if (pos().cartesian(0) <= 0 || pos().cartesian(0) >= size) {
            auxVector = new Vector(auxVelocity1);
            setVel(auxVector);
        }
        if (this.position.cartesian(1) <= 0 || this.position.cartesian(1) >= size) {
            auxVector = new Vector(auxVelocity2);
            setVel(auxVector);
        }
        this.position = currentPosition;
    }

    public void move(double size, double dt) {
        this.treatWalls(size, dt);
        this.updatePosition(dt);
    }

    public void draw() {
        StdDraw.setPenColor(this.color);
        StdDraw.filledCircle(pos().cartesian(0), pos().cartesian(1), radius);
    }

    public static void main(String[] args) {
        /* Espaco na main reservado a eventuais testes desta classe especifica */
    }
}
