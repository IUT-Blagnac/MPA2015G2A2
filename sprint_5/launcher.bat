@Echo off

set SPRINTDIR=%~dp0
set SRCDIR=./src/
set BINDIR=./bin/

@echo ///////////////////////////////////////////////////////
@echo // EXECUTION du GENERATEUR HTML
@echo ///////////////////////////////////////////////////////

cd ./bin/

java -cp ../json-simple-1.1.1.jar main.Main OPTIweb.conf

PAUSE
