package jiva.bytecodegeneration

import jiva.domain.CompilationUnit

class BytecodeGenerator {

    fun generate(compilationUnit: CompilationUnit): ByteArray {
        val classGenerator = ClassGenerator()
        val classWriter = classGenerator.generate(compilationUnit.classDeclaration)
        val result = classWriter.toByteArray()
        return result
    }

}
