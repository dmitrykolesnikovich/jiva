package jiva.bytecodegeneration.expression

import jiva.domain.node.expression.Value
import jiva.util.TypeResolver
import org.objectweb.asm.MethodVisitor

class ValueExpressionGenerator(private val methodVisitor: MethodVisitor) {
    fun generate(value: Value) {
        val type = value.type
        val stringValue = value.value
        val transformedValue = TypeResolver.getValueFromString(stringValue, type)
        methodVisitor.visitLdcInsn(transformedValue)
    }
}