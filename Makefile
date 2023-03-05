Parser.class: Parser.java
    javac Parser.java

Operators.class: Operators.java
    javac Operators.java

Interpret.class: Operators.class Parser.class Interpret.java
    javac Interpret.java

run: Interpret.class
    java Interpret
