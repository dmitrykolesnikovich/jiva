package jiva.bytecodegeneration.expression

import jiva.domain.node.expression.Parameter
import jiva.domain.scope.Scope
import org.objectweb.asm.MethodVisitor

class ParameterExpressionGenerator(private val methodVisitor: MethodVisitor, private val scope: Scope) {

    fun generate(parameter: Parameter) {
        val type = parameter.type
        val index = scope.getLocalVariableIndex(parameter.name)
        methodVisitor.visitVarInsn(type.loadVariableOpcode, index)
    }

}
