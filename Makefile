JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = $(wildcard *.java)

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

format:
	clang-format -style="{BasedOnStyle: google, IndentWidth: 4}" -i $(CLASSES)

