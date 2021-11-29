package jiva.bytecodegeneration.expression

import jiva.domain.node.expression.*
import jiva.domain.node.expression.arthimetic.Addition
import jiva.domain.node.expression.arthimetic.Division
import jiva.domain.node.expression.arthimetic.Multiplication
import jiva.domain.node.expression.arthimetic.Substraction
import jiva.domain.scope.Scope
import org.objectweb.asm.MethodVisitor

/**
 * Created by kuba on 02.04.16.
 */
class ExpressionGenerator(methodVisitor: MethodVisitor, scope: Scope) {

    private val referenceExpressionGenerator = ReferenceExpressionGenerator(methodVisitor, scope)
    private val valueExpressionGenerator = ValueExpressionGenerator(methodVisitor)
    private val callExpressionGenerator = CallExpressionGenerator(this, scope, methodVisitor)
    private val arithmeticExpressionGenerator = ArithmeticExpressionGenerator(this, methodVisitor)
    private val conditionalExpressionGenerator = ConditionalExpressionGenerator(this, methodVisitor)
    private val parameterExpressionGenerator = ParameterExpressionGenerator(methodVisitor, scope)

    fun generate(reference: FieldReference) {
        referenceExpressionGenerator.generate(reference)
    }

    fun generate(reference: LocalVariableReference) {
        referenceExpressionGenerator.generate(reference)
    }

    fun generate(parameter: Parameter) {
        parameterExpressionGenerator.generate(parameter)
    }

    fun generate(value: Value) {
        valueExpressionGenerator.generate(value)
    }

    fun generate(constructorCall: ConstructorCall?) {
        callExpressionGenerator.generate(constructorCall!!)
    }

    fun generate(superCall: SuperCall?) {
        callExpressionGenerator.generate(superCall!!)
    }

    fun generate(functionCall: FunctionCall?) {
        callExpressionGenerator.generate(functionCall!!)
    }

    fun generate(expression: Addition?) {
        arithmeticExpressionGenerator.generate(expression!!)
    }

    fun generate(expression: Substraction?) {
        arithmeticExpressionGenerator.generate(expression!!)
    }

    fun generate(expression: Multiplication?) {
        arithmeticExpressionGenerator.generate(expression!!)
    }

    fun generate(expression: Division?) {
        arithmeticExpressionGenerator.generate(expression!!)
    }

    fun generate(conditionalExpression: ConditionalExpression?) {
        conditionalExpressionGenerator.generate(conditionalExpression!!)
    }

    fun generate(emptyExpression: EmptyExpression?) {
        // no op
    }

}