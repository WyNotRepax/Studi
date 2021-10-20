set REDIST=%~dp0redist
set JAVA_HOME=%REDIST%\jdk8u282-b08
set JDK_HOME=%JAVA_HOME%
set JRE_HOME=%JAVA_HOME%
set MAVEN_HOME=%REDIST%\apache-maven-3.6.3
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
cd %~dp0project
mvn gwt:devmode