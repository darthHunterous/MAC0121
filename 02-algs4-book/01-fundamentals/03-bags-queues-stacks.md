# 1.3 -> BAGS, QUEUES, STACKS

* **APIs**
    * List of possible methods, functions and the respective constructor to the data type
    * **Bags**
        * Constructor, add item, bag emptiness, bag size
    * **FIFO Queue**
        * Constructor, add item, remove oldest item, queue emptiness, queue size
    * **LIFO Stack**
        * Constructor, add item, remove most recent item, stack emptiness, stack size
<br><br>
* **GENERICS**
    * Parametrized types such as Stack&lt;Item&gt;
        * Can be replaced by other data structures
            * Stack&lt;Date&gt;
            * Stack&lt;Pokemon&gt;
    * Capable of using for any type of data
        * Stack&lt;Item&gt; -> stack of items
<br><br>
* **WRAPPER TYPES:**
    * Reference types to primitive types such as Boolean, Byte, Character, Double, Float, Integer, Long, Short
* **AUTOBOXING**
    * Automatic cast from primitive type to wrapper type (int -> Integer)
* **AUTO-UNBOXING**
    * Castinf wrapper type to primitive type (Integer -> int)
* **AUTO**
    * Type parameters have to be instantiated as reference types
<br><br>
* **ITERABLE COLLECTION**
    * Iterate through the items collection with the foreach statement.
        * For example in a Queue&lt;Transaction&gt; collection:
        ```java
        for (Transaction t : collection)
            StdOut.println();
        ```
<br><br>
* **BAGS**
    * Collection without the support to remove items
        * Basically provide clients how to collect items and iterate through the collected items
* **FIFO QUEUE**
    * First in, First out
        * Same policy as many everyday life tasks
* **PUSHDOWN STACK**
    * Last in, Last out
    * Examples of hyperlinks in a browser when we navigate to another page
        * It stacks the previous page in case we need to hit the back button
<br><br>              
* **PARENTHESIZED ARITHMETIC EXPRESSIONS**
    * Djikstra's 2-stack algorithm
        * Push operands to operand stack
        * Push operators onto operator stack
        * Ignore left parentheses
        * Finding a right parenthesis, pop operator and pop enough operands for the operation
<br><br>
* **LINKED LIST**
    * Recursive data structure
    * Null or reference to a node
    ```java
    private class Node {
        Item item;
        Node next;
    }
    ```
    * **Building linked list:** 
        * Set the item field and then the next pointing to next node
    * **Insert at the Beginning:**
        * Easiest place to insert a new node
        * Create a reference to the current list
        * Create a new node to be the first
        * Set the <code>first.next</code> to reference current
    * **Remove from beginning**
            * <code>first = first.next;</code>
    * **Insert at the end:**
        * It's good to keep a node of the end of the linked list
        * Otherwise traversal is required everytime
        * So:
            * Save a link to last node
            * Create a new node for the end
            * Link the new node to the end of the list
            * Traversal: 
                ```java
                for (Node x = first; x != null; x = x.next) {
                    // execute what is needed with x.item
                }
                ```
<br><br>
* **ITERATION EQUIVALENCES:**
    ```java
    // This code is awkward, the formatting was lost before refactoring to Markdown
    Stack<String> collection = new Stack<String>(); 
    Iterator<String> i = collection.iterator();
    // ...
    while (i.hasNext()) {
        for (String s : collection)
            String s = i.next();
        StdOut.println(s);
        // ...
    }
    ```
<br><br>
* **IMPLEMENTING ITERATION:**
    * Import Java Iterator:
        * <code>import java.util.Iterator;</code>
    * Add "implements" to the Java class declaration:
        * <code>public class Stack&lt;Item&gt; implements Iterable&lt;Item&gt;</code>
    * Implement the iterator() method, returning an object from a class that implements the Iterator:
        ```java
        public Iterator<Item> iterator() {
            return new ListIterator();
        }
        ```
    * Implement a nested class with methods hasNext(), next() and remove():
        ```java
        private class ListIterator<Item> implements Iterator<Item>
        ```
    * The remove() method is better not to be implemented as modifying the data structure while iterating might not be a good idea.