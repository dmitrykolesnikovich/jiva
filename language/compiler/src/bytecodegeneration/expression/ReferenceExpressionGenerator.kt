package jiva.bytecodegeneration.expression

import jiva.domain.node.expression.FieldReference
import jiva.domain.node.expression.LocalVariableReference
import jiva.domain.scope.Scope
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class ReferenceExpressionGenerator(private val methodVisitor: MethodVisitor, private val scope: Scope) {

    fun generate(localVariableReference: LocalVariableReference) {
        val varName = localVariableReference.name
        val index = scope.getLocalVariableIndex(varName)
        val type = localVariableReference.type
        methodVisitor.visitVarInsn(type.loadVariableOpcode, index)
    }

    fun generate(fieldReference: FieldReference) {
        val varName = fieldReference.name
        val type = fieldReference.type
        val ownerInternalName = fieldReference.ownerInternalName
        val descriptor = type.descriptor
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
        methodVisitor.visitFieldInsn(Opcodes.GETFIELD, ownerInternalName, varName, descriptor)
    }

}