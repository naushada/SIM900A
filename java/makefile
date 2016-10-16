FLAGS = -g -Xlint
JC    = javac
CLASS_DIR = ../classes
CLASS_PATH = -classpath $(CLASS_DIR)

.SUFFIXES: .java .class
SRC = $(shell find . -type f -name '*.java')

.java.class:
		$(JC) $(FLAGS) -d $(CLASS_DIR) $<


default: classes
classes: $(SRC:.java=.class)

clean:
		$(RM) $(CLASS_DIR)/*.class
