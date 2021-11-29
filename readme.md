# Jiva Programming Language

JVM language from scratch written in Java. Kotlin port of https://github.com/JakubDziworski/Enkel-JVM-language

# How to Build

```
gradlew jiva:compiler:installShadowDist
```

# How to Test

```
java -cp .:language/compiler/build/install/compiler-shadow/lib/compiler.jar jiva.Compiler showcase/res/DefaultParamTest.jiva
```
