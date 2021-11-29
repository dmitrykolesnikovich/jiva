package jiva.bytecodegeneration

import jiva.domain.scope.Field
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

/**
 * Created by kuba on 13.05.16.
 */
class FieldGenerator(private val classWriter: ClassWriter) {
    fun generate(field: Field) {
        val name = field.name
        val descriptor = field.type.descriptor
        val fieldVisitor = classWriter.visitField(Opcodes.ACC_PUBLIC, name, descriptor, null, null)
        fieldVisitor.visitEnd()
    }
}