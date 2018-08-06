/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Édio Cerati Neto
 * Numero USP: 9762678
 * Tarefa: Embaralhando listas ligadas
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

/******************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue < input.txt
 *  Data files:   http://introcs.cs.princeton.edu/43stack/tobe.txt
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java Queue < tobe.txt
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code Queue} class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the top item,
 *  testing if the queue is empty, getting the number of items in the
 *  queue, and iterating over the items in FIFO order.
 *  <p>
 *  This implementation uses a singly-linked list with a nested class for
 *  linked-list nodes.
 *  The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="http://introcs.cs.princeton.edu/43stack">Section 4.3</a> of
 *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Item> the generic type of an item in this queue
 */
public class Queue<Item> implements Iterable<Item> {
    private int n;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int length() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

   /**
     * Add the item to the queue.
     */
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }


    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

/* O algoritmo presente na funcao abaixo esta de acordo com os requerimentos
do enunciado, pois apresenta tempo de execucao linearitmico (uma vez que se vale
do metodo da divisao e conquista, fazendo chamadas recursivas com metade dos
valores de cada lista ligada) e armazenamento adicional logaritmico, ja que vai
transportando os elementos de uma lista a outra, atraves de empilhamentos e
desempilhamentos sucessivos.
    Ademais, o rearranjo e' uniforme, pois utiliza a funcao StdRandom.bernoulli(),
que retorna true ou false com 50% de chance cada. Para garantir isso no caso
em que a lista ligada tem tamanho impar, acrescenta um Node dummy, de forma a
tornar todas as sublistas tambem pares.
*/
    public void shuffle() {
        if (this.n == 1) // caso base da recursao
            return;

        int randomLocation;
        int halfLength = this.n/2; /* armazena o valor equivalente a metade
        do comprimento para evitar repetir a mesma operacao */

        Queue<Item> firstHalf = new Queue<Item>();

        for (int i = 1; i <= halfLength; i++) {
            firstHalf.enqueue(this.dequeue());
        } // transfere os elementos da lista para a lista da primeira metade

        Queue<Item> secondHalf = new Queue<Item>();

        while (this.last != null) {
            secondHalf.enqueue(this.dequeue());
        } // transfere o restante da lista original para a lista da segunda metade

        //embaralhamento recursivo de cada metade
        firstHalf.shuffle();
        secondHalf.shuffle();

        /* bloco responsavel por inserir um Node dummy no meio da lista ligada
        para garantir que cada metade tenha a mesma paridade, isto e' feito
        de maneira aleatoria */
        Node dummy = new Node();
        if (this.n % 2 != 0) {
            randomLocation = StdRandom.uniform(1, halfLength + 2);
            if (randomLocation == 1) {
                dummy.item = firstHalf.peek();
                dummy.next = firstHalf.first;
            }
            else {
                Node aux = firstHalf.first;
                for (int i = 1; i < halfLength; i++)
                    if (randomLocation == i + 1) {
                        dummy.item = firstHalf.peek();
                        dummy.next = aux.next;
                        aux.next = dummy;
                    }
                    aux = aux.next;
            }
        }

        /* Utiliza a funcao aleatoria com base na distribuicao de probabilidade
        de Bernoulli para inserir os elementos de cada metade na lista principal */
        while (!firstHalf.isEmpty() && !secondHalf.isEmpty()) {
            if (StdRandom.bernoulli() == true)
                this.enqueue(firstHalf.dequeue());
            else
                this.enqueue(secondHalf.dequeue());
        }

        /* Caso a funcao aleatoria de Bernoulli apresente pequeno desvio em
        relacao ao esperado pela probabilidade 50/50 (o que provavelmente ocorre
        para um numero pequeno de elementos na lista, insere o restante dos
        elementos na lista principal */
        if (!firstHalf.isEmpty())
            this.last.next = firstHalf.first;
        if (!secondHalf.isEmpty())
            this.last.next = secondHalf.first;

        // remove o elemento dummy da lista
        if (this.n % 2 != 0) {
            Node aux = this.first;
            while (aux.next != null && aux.next != dummy)
                aux = aux.next;
            aux.next = dummy.next;
        }
    }

    /**
     * Unit tests the {@code Queue} data type.
     */
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
