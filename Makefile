JFLAGS = -g
JC = javac
.SUFFIXES: .java .class .gnuplot .png
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = $(wildcard *.java)

PLOTS = $(wildcard plot/*.gnuplot)

default: classes

all: classes plots

classes: $(CLASSES:.java=.class)

plots:
	gnuplot $(PLOTS)


clean:
	$(RM) *.class out/*.out plot/*.png

format:
	clang-format \
	-style="{BasedOnStyle: google, IndentWidth: 4, ColumnLimit: 80}" \
	-i $(CLASSES)

