# smethods

Method level dependency graph.

## Requirements
Java 8

## Usage
```
mvn test-compile
mvn exec:java -Dexec.mainClass=org.smethods.MethodLevelStaticDepsBuilder -Dexec.args="path to test classes"
```

## Background
1. [ASM](http://asm.ow2.org/)
2. [Method level dependency graph](http://www.cs.umd.edu/~pugh/java/methods.html)