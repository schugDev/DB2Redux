#!/bin/bash
ant -f $PWD -Dnb.internal.action.name=rebuild clean jar 
java -jar "$PWD/dist/DBPraktikum.jar"
