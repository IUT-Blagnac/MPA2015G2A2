@echo off 

call makejava.bat
call launcher.bat
call makeTest.bat
PAUSE
call makeDoc.bat

call OpenOpti.bat