package jiva.bytecodegeneration

import jiva.domain.ClassDeclaration
import jiva.domain.Function
import jiva.domain.scope.Field
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import java.util.function.Consumer

class ClassGenerator {

    private val classWriter: ClassWriter = ClassWriter(ClassWriter.COMPUTE_FRAMES + ClassWriter.COMPUTE_MAXS)

    fun generate(classDeclaration: ClassDeclaration): ClassWriter {
        val name = classDeclaration.name
        classWriter.visit(CLASS_VERSION, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, name, null, "java/lang/Object", null)
        val methods = classDeclaration.methods
        val fields: Collection<Field> = classDeclaration.fields
        val fieldGenerator = FieldGenerator(classWriter)
        fields.forEach(Consumer { f: Field -> f.accept(fieldGenerator) })
        val methodGenerator = MethodGenerator(classWriter)
        methods.forEach(Consumer { f: Function -> f.accept(methodGenerator) })
        classWriter.visitEnd()
        return classWriter
    }

    companion object {
        private const val CLASS_VERSION = 52
    }

}