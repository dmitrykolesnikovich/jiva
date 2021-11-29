# Jiva Programming Language

JVM language from scratch written in Java. Kotlin port of https://github.com/JakubDziworski/Enkel-JVM-language

# Build Compiler

```
gradlew jiva:compiler:installShadowDist
```

# Compile Test

```
java -cp .:language/compiler/build/install/compiler-shadow/lib/compiler.jar jiva.Compiler showcase/res/DefaultParamTest.jiva
```

# Run Test

```
java DefaultParamTest
```
