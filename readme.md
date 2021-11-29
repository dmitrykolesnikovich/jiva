# Jiva Programming Language

JVM language from scratch written in Java. Kotlin port of https://github.com/JakubDziworski/Enkel-JVM-language

# Build Compiler

```
gradlew jiva:compiler:installShadowDist
```

# Compile Showcase

```
java -jar language/compiler/build/install/compiler-shadow/lib/compiler.jar showcase/res/DefaultParamTest.jiva
```

# Run Showcase

```
java DefaultParamTest
```
