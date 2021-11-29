package jiva.bytecodegeneration.statement

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.domain.node.statement.Assignment
import jiva.domain.scope.Scope
import jiva.domain.type.Type
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class AssignmentStatementGenerator(val methodVisitor: MethodVisitor, val expressionGenerator: ExpressionGenerator, val scope: Scope) {

    fun generate(assignment: Assignment) {
        val varName = assignment.varName
        val expression = assignment.expression
        val type = expression.type
        if (scope.isLocalVariableExists(varName)) {
            val index = scope.getLocalVariableIndex(varName)
            val localVariable = scope.getLocalVariable(varName)
            val localVariableType = localVariable.type
            castIfNecessary(type, localVariableType)
            methodVisitor.visitVarInsn(type.storeVariableOpcode, index)
            return
        }
        val field = scope.getField(varName)
        val descriptor = field.type.descriptor
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
        expression.accept(expressionGenerator)
        castIfNecessary(type, field.type)
        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD, field.ownerInternalName, field.name, descriptor)
    }

    /*internals*/

    private fun castIfNecessary(expressionType: Type, variableType: Type) {
        if (expressionType != variableType) {
            methodVisitor.visitTypeInsn(Opcodes.CHECKCAST, variableType.internalName)
        }
    }

}
