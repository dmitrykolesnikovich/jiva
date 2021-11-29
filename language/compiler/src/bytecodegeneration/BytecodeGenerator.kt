package jiva.bytecodegeneration

import jiva.domain.CompilationUnit

class BytecodeGenerator {

    fun generate(compilationUnit: CompilationUnit): ByteArray {
        val classDeclaration = compilationUnit.classDeclaration
        val classGenerator = ClassGenerator()
        return classGenerator.generate(classDeclaration).toByteArray()
    }

}
