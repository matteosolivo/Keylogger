# Java-Keylogger
A simple key logger application implemented in Java. It uses [Native Hook](https://github.com/kwhat/jnativehook) library to add global listener for key presses.

## How to Build and Run Project
Build project: 
```bash
mvn package
```
Run ./target/keylogger-jar-with-dependencies.jar file using command:
```bash
java -jar ./target/keylogger-jar-with-dependencies.jar
```
The keys will be written in **C:/keylogger/keys.txt** file and application logs will reside inside **all.log** (commented rows) file.

## P.S.
Don't forget to stop the key logger application after you've done logging.
