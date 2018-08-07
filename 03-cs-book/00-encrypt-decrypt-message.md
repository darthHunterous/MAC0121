# Lecture 0 - A Simple Machine
## Encrypt and Decrypt message

* [Based in Lecture 0](https://introcs.cs.princeton.edu/java/lectures/keynote/CS.0.Prologue.pdf)
<br>
* **Encrypting passphrase "EASY" using the one time pad "0123"**
    * "EASY" translates to binary as
        * E 000100
        * A 000000
        * S 010010
        * Y 011000
    * "0123" translates to binary as:
        * 110100 110101 110110 110111
* The one time pad should be inacessible for most users
    * Basically, only the two communicators should have access to the key.
* **Encryption happens with a XOR**
    * We compare each bit from both message and the pad
        * <code>000100 000000 010010 011000</code>
        * <code>110100 110101 110110 110111</code>
    * Which results as 1 if they are different or 0 when they are equal
        * <code>110000 110101 100100 101111</code>
    * Translating back to ASCII or UNICODE
        * We have **"w1kv"** as the result
<br><br>
* **Decryption:**
    * Works the same way as the encryption
    * Just pass the result "w1kv" through a XOR again with the pad and it will return the original message "EASY"
    * It works because <code>(m +o k) +o k = m</code>
        * [Where <code>+o</code> represents XOR]
    * This can easily be proved or verified through the use of a truth table, or even better with Boolean Algebra
        * We know
            * <code>k +o k = 0</code>
            * <code>m +o 0 = m</code>
        * Thus, we can conclude that
            * <code>(m +o k) +o k = m +o (k +o k)</code>
            * <code>(m +o k) +o k = m +o 0</code>
            * <code>(m +o k) +o k = m</code>
<br><br>
* **One Time Pad Security:**
    * Efficiency and security:
        * Pads must be random and used only once
    * **Advantages:**
        * Simplicity
        * Same method for encryption and decryption (XOR)
        * Unbreakable with random bits for the one time pad
    * **Disadvantages:**
        * Easily breakable if it gets reused
        * Hard to generate random bits with the same size as the message 
        * Key distribuction needs to occur through other channel