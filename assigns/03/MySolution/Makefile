###########################################################################
JAVA = java
JAVAC = javac

SRC = .
JAR = ./../../../assets/algs4/algs4.jar
ASSIGNS02 = ./../../02/MySolution
CLASS_PATHS = $(JAR):$(ASSIGNS02):$(SRC)


Assign03_01: Assign03_01.class;
	$(JAVA) Assign03_01 -cp $(CLASS_PATHS)
Assign03_02: Assign03_02.class;
	$(JAVA) Assign03_02 -cp $(CLASS_PATHS)
Assign03_03: Assign03_03.class;
	$(JAVA) Assign03_03 -cp $(CLASS_PATHS)
Assign03_04: Assign03_04.class;
	$(JAVA) Assign03_04 -cp $(CLASS_PATHS)
Assign03_05: Assign03_05.class;
	$(JAVA) Assign03_05 -cp $(CLASS_PATHS)

Assign03_01.class: Assign03_01.java;
	$(JAVAC) Assign03_01.java -cp $(CLASS_PATHS)
Assign03_02.class: Assign03_02.java;
	$(JAVAC) Assign03_02.java -cp $(CLASS_PATHS)
Assign03_03.class: Assign03_03.java;
	$(JAVAC) ./../../02/MySolution/Deque.java -d $(SRC)
	$(JAVAC) ./../../02/MySolution/Assign02_03.java -d $(SRC)
	$(JAVAC) Assign03_03.java -cp $(CLASS_PATHS)
Assign03_04.class: Assign03_04.java;
	$(JAVAC) ./../../02/MySolution/Deque.java -d .
	$(JAVAC) ./../../02/MySolution/Assign02_04.java -d .
	$(JAVAC) Assign03_04.java -cp $(CLASS_PATHS)
Assign03_05.class: Assign03_05.java;
	$(JAVAC) Assign03_05.java -cp $(CLASS_PATHS)

clean:
	rm -f *.class
###########################################################################
###########################################################################
###########################################################################

