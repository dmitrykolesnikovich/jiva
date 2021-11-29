package jiva.bytecodegeneration.expression

import jiva.domain.CompareSign
import jiva.domain.node.expression.*
import jiva.domain.scope.FunctionSignature
import jiva.domain.type.BultInType
import jiva.domain.type.ClassType
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class ConditionalExpressionGenerator(val expressionGenerator: ExpressionGenerator, val methodVisitor: MethodVisitor) {

    fun generate(conditionalExpression: ConditionalExpression) {
        val leftExpression = conditionalExpression.leftExpression
        val rightExpression = conditionalExpression.rightExpression
        val compareSign = conditionalExpression.compareSign
        if (conditionalExpression.isPrimitiveComparison) {
            generatePrimitivesComparison(leftExpression, rightExpression, compareSign)
        } else {
            generateObjectsComparison(leftExpression, rightExpression, compareSign)
        }
        val endLabel = Label()
        val trueLabel = Label()
        methodVisitor.visitJumpInsn(compareSign.opcode, trueLabel)
        methodVisitor.visitInsn(Opcodes.ICONST_0)
        methodVisitor.visitJumpInsn(Opcodes.GOTO, endLabel)
        methodVisitor.visitLabel(trueLabel)
        methodVisitor.visitInsn(Opcodes.ICONST_1)
        methodVisitor.visitLabel(endLabel)
    }

    /*internals*/

    private fun generateObjectsComparison(leftExpression: Expression, rightExpression: Expression, compareSign: CompareSign) {
        val parameter = Parameter("o", ClassType("java.lang.Object"), null)
        val parameters = listOf(parameter)
        val argument = Argument(rightExpression, null)
        val arguments = listOf(argument)
        when (compareSign) {
            CompareSign.EQUAL, CompareSign.NOT_EQUAL -> {
                val equalsSignature = FunctionSignature("equals", parameters, BultInType.BOOLEAN)
                val equalsCall = FunctionCall(equalsSignature, arguments, leftExpression)
                equalsCall.accept(expressionGenerator)
                methodVisitor.visitInsn(Opcodes.ICONST_1)
                methodVisitor.visitInsn(Opcodes.IXOR)
            }
            CompareSign.LESS, CompareSign.GREATER, CompareSign.LESS_OR_EQUAL, CompareSign.GRATER_OR_EQAL -> {
                val compareToSignature = FunctionSignature("compareTo", parameters, BultInType.INT)
                val compareToCall = FunctionCall(compareToSignature, arguments, leftExpression)
                compareToCall.accept(expressionGenerator)
            }
        }
    }

    private fun generatePrimitivesComparison(leftExpression: Expression, rightExpression: Expression, compareSign: CompareSign) {
        leftExpression.accept(expressionGenerator)
        rightExpression.accept(expressionGenerator)
        methodVisitor.visitInsn(Opcodes.ISUB)
    }

}