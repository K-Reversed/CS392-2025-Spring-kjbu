########################################################################
#
# A simple Makefile for compiling Java code for CS392X1
#
########################################################################
JAVA=java
JAVAC=javac
CLASSPATH = -cp .
########################################################################
JAR_FILE=../../../assets/algs4/algs4.jar
########################################################################
all::
all::
	TestPayroll
########################################################################
TestPayroll: TestPayroll.class;
	$(JAVA) -cp $(CLASS_PATHS) $(CLASSPATH) TestPayroll
TestPayroll.class: TestPayroll.java;
	$(JAVAC) -cp $(CLASS_PATHS) $(CLASSPATH) TestPayroll.java
########################################################################
clean:
	rm -f *.class
########################################################################
cleanall:: clean
########################################################################
########################################################################
########################################################################
