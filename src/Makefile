run: Interpret.class
	java Interpret

help: Helper.class
	java Helper

Parser.class: Parser.java
	javac Parser.java

Operators.class: Operators.java
	javac Operators.java

Interpret.class: Operators.class Parser.class Interpret.java
	javac Interpret.java

Helper.class: Helper.java
	javac Helper.java

clean:
	rm -f *.class
