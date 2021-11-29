package jiva.bytecodegeneration

import jiva.domain.CompilationUnit

/**
 * Created by kuba on 01.04.16.
 */
class BytecodeGenerator {
    fun generate(compilationUnit: CompilationUnit): ByteArray {
        val classDeclaration = compilationUnit.classDeclaration
        val classGenerator = ClassGenerator()
        return classGenerator.generate(classDeclaration).toByteArray()
    }
}