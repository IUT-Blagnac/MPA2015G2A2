@Echo off
set SPRINTDIR=%~dp0
set SRCDIR=./src/
set BINDIR=./bin/

@echo ///////////////////////////////////////////////////////
@echo // compilation des .java
@echo ///////////////////////////////////////////////////////

rem cd %SRCDIR%
javac -Xlint:unchecked -cp .;./json-simple-1.1.1.jar;./src -d %BINDIR% @files.txt

rem cd ..

pause

