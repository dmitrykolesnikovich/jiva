package jiva.bytecodegeneration.statement

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.domain.node.statement.IfStatement
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class IfStatementGenerator(val statementGenerator: StatementGenerator, val expressionGenerator: ExpressionGenerator, val methodVisitor: MethodVisitor) {

    fun generate(ifStatement: IfStatement) {
        val condition = ifStatement.condition
        condition.accept(expressionGenerator)
        val trueLabel = Label()
        val endLabel = Label()
        methodVisitor.visitJumpInsn(Opcodes.IFNE, trueLabel)
        val falseStatement = ifStatement.falseStatement
        if (falseStatement.isPresent) {
            falseStatement.get().accept(statementGenerator)
        }
        methodVisitor.visitJumpInsn(Opcodes.GOTO, endLabel)
        methodVisitor.visitLabel(trueLabel)
        ifStatement.trueStatement.accept(statementGenerator)
        methodVisitor.visitLabel(endLabel)
    }

}
