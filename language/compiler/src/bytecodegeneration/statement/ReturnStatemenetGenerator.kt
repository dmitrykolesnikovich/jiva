package jiva.bytecodegeneration.statement

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.domain.node.statement.ReturnStatement
import org.objectweb.asm.MethodVisitor

class ReturnStatemenetGenerator(val expressionGenerator: ExpressionGenerator, val methodVisitor: MethodVisitor) {

    fun generate(returnStatement: ReturnStatement) {
        val expression = returnStatement.expression
        val type = expression.type
        expression.accept(expressionGenerator)
        methodVisitor.visitInsn(type.returnOpcode)
    }

}
