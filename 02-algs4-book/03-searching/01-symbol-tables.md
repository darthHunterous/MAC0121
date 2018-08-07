# Chapter 03 - Searching
## Section 01 - Symbol Tables

* **SYMBOL TABLE:**
    * Associates a value to a key
        * Value is the goal of the search
    * *Dictionary*
        * "Word" key has the "definition" value
    * *Book Index*
        * Purpose of finding pages with relevant subject
        * "Term/subject" is the key and "list of pages" is the value
    * *Web Searcher*
        * Purpose of finding relevant webpages
        * "Keyword to search" is the key
        * "List of page names" is the value
<br><br>
* **Symbol Tables API:**
    * Constructor
    * Put key-value into the table
    * Value paired with certain key
    * Delete key from the table
    * Find if the ST contains a value for a given key
    * Check if table is empty or not
    * Size of ST
    * Iterable for all the keys in the table
<br><br>
* **Implementation Standards for Symbol Tables:** 
    * **Generics:**
        * Better compatibility and maintenance
    * **Duplicate Keys:**
        * Can't be defined
        * If a new value needs to be associated with the same key, it removes the old one from the list
    * **Associative array abstraction:**
        * Symbol Table could be thought of as an array.
    * **Null Values:**
        * Keys can't be associated with null value
            * This way we can easily test key and value association
        * We call put() method with null as the value to implement deletion.
            * Null replaces any other eventual value
    * **Deletion:** 
        * *Lazy deletion*
            * Keys are associated with null value. 
        * *Eager deletion*
            * Remove the key from the table.
    * **Iterators:**
        * We must be able to iterate throughout the keys
    * **Key Equality:** objects must implement equals() method
        * **Equivalence Relation:**
            * **Reflexive:** 
                * x.equals(x) is true
            * **Symmetric:**
                * x.equals(y) <-> y.equals(x) is true
            * **Transitive:** 
                * x.equals(y) && y.equals(z) -> x.equals(z)
        * **Consistency:**
            * x.equals(y) always return the same value if object is not modified
        * **Not Null:**
            * x.equals(null) returns false
        * **Immutable Keys:**
            * Guarantees consistency
<br><br>
* **Ordered Symbol Table:**
    * Extends the api for the ordinary ST
    * **API:**
        * <code>min()</code>
            * Smallest key
        * <code>max()</code> 
            * Largest key
        * <code>floor(key)</code>
            * Largest key less than or equal to key
        * <code>ceiling(key)</code>
            * Smallest key greater than or equal to key
        * <code>rank(key)</code>
            * Ammount of keys less than key
        * <code>select(k)</code>
            * Key of rank k
        * <code>deleteMin()</code>
            * Delete smallest key
        * <code>deleteMax()</code>
            * Delete largest key
        * <code>size(low, high)</code>
            * Ammount of keys in the interval [low, high]
        * <code>keys(low, high)</code>
            * Iterable in sorted order for the interval [low, high]
        * <code>keys()</code>
            * Iterable of all keys in sorted order
    * **Exceptional Cases:**
        * Throw exception if no key fits the description in the table
    * **Key Equality**
        * <code>compareTo()</code> must be consistent with <code>equals()</code>
            * <code>(a.compareTo(b) == 0) == (a.equals(b))</code>
        
        
    
