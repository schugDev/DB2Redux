#!/bin/bash
ant -f /home/student/Dokumente/DB2Redux -Dnb.internal.action.name=rebuild clean jar
java -jar "/home/student/Dokumente/DB2Redux/dist/DBPraktikum.jar"
