package jiva.bytecodegeneration.statement

import jiva.domain.node.statement.RangedForStatement
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.domain.CompareSign
import jiva.domain.node.expression.ConditionalExpression
import jiva.domain.node.expression.Expression
import jiva.domain.node.expression.LocalVariableReference
import jiva.domain.scope.LocalVariable
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class ForStatementGenerator(private val methodVisitor: MethodVisitor) {
    fun generate(rangedForStatement: RangedForStatement) {
        val newScope = rangedForStatement.scope
        val scopeGeneratorWithNewScope = StatementGenerator(methodVisitor, newScope)
        val exprGeneratorWithNewScope = ExpressionGenerator(methodVisitor, newScope)
        val iterator = rangedForStatement.iteratorVariableStatement
        val incrementationSection = Label()
        val decrementationSection = Label()
        val endLoopSection = Label()
        val iteratorVarName = rangedForStatement.iteratorVarName
        val endExpression = rangedForStatement.endExpression
        val variable = LocalVariable(iteratorVarName, rangedForStatement.type)
        val iteratorVariable: Expression = LocalVariableReference(variable)
        val iteratorGreaterThanEndConditional =
            ConditionalExpression(iteratorVariable, endExpression, CompareSign.GREATER)
        val iteratorLessThanEndConditional = ConditionalExpression(iteratorVariable, endExpression, CompareSign.LESS)
        iterator.accept(scopeGeneratorWithNewScope)
        iteratorLessThanEndConditional.accept(exprGeneratorWithNewScope)
        methodVisitor.visitJumpInsn(Opcodes.IFNE, incrementationSection)
        iteratorGreaterThanEndConditional.accept(exprGeneratorWithNewScope)
        methodVisitor.visitJumpInsn(Opcodes.IFNE, decrementationSection)
        methodVisitor.visitLabel(incrementationSection)
        rangedForStatement.statement.accept(scopeGeneratorWithNewScope)
        methodVisitor.visitIincInsn(newScope.getLocalVariableIndex(iteratorVarName), 1)
        iteratorGreaterThanEndConditional.accept(exprGeneratorWithNewScope)
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, incrementationSection)
        methodVisitor.visitJumpInsn(Opcodes.GOTO, endLoopSection)
        methodVisitor.visitLabel(decrementationSection)
        rangedForStatement.statement.accept(scopeGeneratorWithNewScope)
        methodVisitor.visitIincInsn(newScope.getLocalVariableIndex(iteratorVarName), -1)
        iteratorLessThanEndConditional.accept(exprGeneratorWithNewScope)
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, decrementationSection)
        methodVisitor.visitLabel(endLoopSection)
    }
}