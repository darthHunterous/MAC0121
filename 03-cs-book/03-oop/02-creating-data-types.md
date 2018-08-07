# Chapter 03 - OOP
## Section 02 - Creating Data Types

* <code>println()</code> function receiving an object that isn't a String, will search for its toString() method, which will describe how to print the given object as a String
<br><br>
* Lecture Example:
    ```java
    public String toString() {
    return q + " at " + "(" + rx + ", " + ry + ")";
    }
    ```
    * At main:
        ```java
        public static void main(String[] args) {
            // Charge(rx, ry, q)
            Charge c = new Charge(.72, .31, 21.3);
            StdOut.println(c);
        }
        ```
    * Output is "21.3 at (0.72, 0.31)" accordingly to toString();
    * It isn't necessary to use <code>@override</code>
        * The toString() method in the object class automatically overrides inbuilt Java's
        * c is a Charge object
            ```java
            StdOut.println(c);
            // equivalent to 
            StdOut.println(c.toString());
            ```