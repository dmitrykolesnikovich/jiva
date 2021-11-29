package jiva.bytecodegeneration.statement

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.domain.node.statement.PrintStatement
import jiva.domain.type.ClassType
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class PrintStatementGenerator(
    private val expressionGenerator: ExpressionGenerator,
    private val methodVisitor: MethodVisitor
) {
    fun generate(printStatement: PrintStatement) {
        val expression = printStatement.expression
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;")
        expression.accept(expressionGenerator)
        val type = expression.type
        val descriptor = "(" + type.descriptor + ")V"
        val owner = ClassType("java.io.PrintStream")
        val fieldDescriptor = owner.descriptor
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, fieldDescriptor, "println", descriptor, false)
    }
}