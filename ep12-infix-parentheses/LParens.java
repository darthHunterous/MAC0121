/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Exercício 1.3.9 de Algs4
 * Data: 25/10/2017
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

public class LParens {

    /* Lê da entrada padrão uma expressão sem parenteses a esquerda e a devolve
       na notacão infixa correspondente */
    public static void main(String[] args) {
        // Armazena cada elemento da expressão como uma string em um vetor
        // Ignora espaços em branco
        String[] expression = StdIn.readAllStrings();
        String[] infix = new String[expression.length]; // representa a pilha
        int top = -1; // indica a pilha vazia no começo

        for (int i = 0; i < expression.length; i++) {
            if (expression[i].equals(")")) { // empilha os 3 elementos do topo da pilha ao redor de parenteses
                infix[top-2] = "( " + infix[top-2] + " " + infix[top-1] + " " + infix[top] + " )";
                top -= 2;
            }
            else { // empilha apenas o elemento atual no topo
                infix[top+1] = expression[i];
                top++;
            }
        } /* pressupondo a entrada da expressao correta, a pilha contera apenas
        um elemento equivalente a notacao infixa da expressao inteira */

        StdOut.println(infix[0]);
    }
}
