@echo off

set LIB_HOME=..\lib
set XERCES=%LIB_HOME%\xerces.jar
set XERCESSAMPLES=%LIB_HOME%\xercesSamples.jar
set CP=.;%LIB_HOME%;%XERCES%;%XERCESSAMPLES%

java -cp %CP% SAXV -v %1