JCC = javac
JFLAGS = -g # for debugging information

default: randmst.class

randmst.class: randmst.java
	$(JCC) $(JFLAGS) randmst.java

# Removes all .class files, so that the next make rebuilds them
clean:
	$(RM) *.class
	rm Manifest
