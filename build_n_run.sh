#! /bin/bash

mvn clean compile assembly:single
java -jar /home/nrv/dev/jfxRepertoire/target/jfxchess-4.3-jar-with-dependencies.jar
