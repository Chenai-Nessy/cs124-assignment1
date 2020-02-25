JCC = javac
JFLAGS = -g # for debugging information

randmst: randmst.class

randmst.class: randmst.java
	$(JCC) $(JFLAGS) randmst.java
	java randmst

# Removes all .class files, so that the next make rebuilds them
clean:
	$(RM) *.class
