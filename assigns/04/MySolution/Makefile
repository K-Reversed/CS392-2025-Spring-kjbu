###########################################################################
###########################################################################
JAVA = java
JAVAC = javac

SRC = .
JAR = ./../../../assets/algs4/algs4.jar
CLASS_PATHS = $(JAR):$(SRC)


Assign04_01: Assign04_01.class;
	$(JAVA) Assign04_01 -cp $(CLASS_PATHS)
Assign04_02: Assign04_02.class;
	$(JAVA) Assign04_02 -cp $(CLASS_PATHS)
Assign04_03: Assign04_03.class;
	$(JAVA) Assign04_03 -cp $(CLASS_PATHS)
Assign04_04: Assign04_04.class;
	$(JAVA) Assign04_04 -cp $(CLASS_PATHS)

Assign04_01.class: Assign04_01.java;
	$(JAVAC) Assign04_01.java -cp $(CLASS_PATHS)
Assign04_02.class: Assign04_02.java;
	$(JAVAC) Assign04_02.java -cp $(CLASS_PATHS)
Assign04_03.class: Assign04_03.java;
	$(JAVAC) Assign04_03.java -cp $(CLASS_PATHS)
Assign04_04.class: Assign04_04.java;
	$(JAVAC) Assign04_04.java -cp $(CLASS_PATHS)
clean:
	rm -f *.class
###########################################################################
###########################################################################
###########################################################################