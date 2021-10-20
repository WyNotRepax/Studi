rmdir /s "./simpleshoppingapp"
java -cp "./lib/derbyshared.jar;./lib/derby.jar;./lib/derbytools.jar;./lib/derbyoptionaltools.jar;" org.apache.derby.tools.ij < setupDatabase.sql