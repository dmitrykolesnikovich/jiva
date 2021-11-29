package jiva.bytecodegeneration.expression

import jiva.domain.node.expression.arthimetic.*
import jiva.domain.type.BultInType
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class ArithmeticExpressionGenerator(val expressionGenerator: ExpressionGenerator, val methodVisitor: MethodVisitor) {

    fun generate(expression: Addition) {
        if (expression.type == BultInType.STRING) {
            generateStringAppend(expression)
            return
        }

        evaluateArithmeticComponents(expression)
        val type = expression.type
        methodVisitor.visitInsn(type.addOpcode)
    }

    fun generate(expression: Substraction) {
        val type = expression.type
        evaluateArithmeticComponents(expression)
        methodVisitor.visitInsn(type.substractOpcode)
    }

    fun generate(expression: Multiplication) {
        evaluateArithmeticComponents(expression)
        val type = expression.type
        methodVisitor.visitInsn(type.multiplyOpcode)
    }

    fun generate(expression: Division) {
        evaluateArithmeticComponents(expression)
        val type = expression.type
        methodVisitor.visitInsn(type.dividOpcode)
    }

    /*internals*/

    private fun evaluateArithmeticComponents(expression: ArthimeticExpression) {
        val leftExpression = expression.leftExpression
        val rightExpression = expression.rightExpression
        leftExpression.accept(expressionGenerator)
        rightExpression.accept(expressionGenerator)
    }

    private fun generateStringAppend(expression: Addition) {
        methodVisitor.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder")
        methodVisitor.visitInsn(Opcodes.DUP)
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)
        val leftExpression = expression.leftExpression
        leftExpression.accept(expressionGenerator)
        val leftExprDescriptor = leftExpression.type.descriptor
        var descriptor = "($leftExprDescriptor)Ljava/lang/StringBuilder;"
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", descriptor, false)
        val rightExpression = expression.rightExpression
        rightExpression.accept(expressionGenerator)
        val rightExprDescriptor = rightExpression.type.descriptor
        descriptor = "($rightExprDescriptor)Ljava/lang/StringBuilder;"
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", descriptor, false)
        methodVisitor.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "java/lang/StringBuilder",
            "toString",
            "()Ljava/lang/String;",
            false
        )
    }

}