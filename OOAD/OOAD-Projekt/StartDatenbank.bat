set REDIST=%~dp0redist
set JAVA_HOME=%REDIST%\jdk8u282-b08
set JDK_HOME=%JAVA_HOME%
set JRE_HOME=%JAVA_HOME%
set MAVEN_HOME=%REDIST%\apache-maven-3.6.3
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
cd %~dp0project\db
java -classpath %REDIST%\hsqldb-2.5.1\hsqldb\lib\hsqldb.jar org.hsqldb.server.Server