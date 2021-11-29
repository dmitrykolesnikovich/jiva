package jiva.parsing.visitor.expression

import jiva.domain.node.expression.ConditionalExpression
import jiva.domain.node.expression.Expression
import jiva.domain.scope.Scope
import jiva.parsing.visitor.expression.function.CallExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.*

/**
 * Created by kuba on 02.04.16.
 */
class ExpressionVisitor(scope: Scope) : JivaBaseVisitor<Expression?>() {

    private val arithmeticExpressionVisitor: ArithmeticExpressionVisitor = ArithmeticExpressionVisitor(this)
    private val variableReferenceExpressionVisitor: VariableReferenceExpressionVisitor = VariableReferenceExpressionVisitor(scope)
    private val valueExpressionVisitor: ValueExpressionVisitor = ValueExpressionVisitor()
    private val callExpressionVisitor: CallExpressionVisitor = CallExpressionVisitor(this, scope)
    private val conditionalExpressionVisitor: ConditionalExpressionVisitor = ConditionalExpressionVisitor(this)

    override fun visitVarReference(ctx: VarReferenceContext): Expression {
        return variableReferenceExpressionVisitor.visitVarReference(ctx)
    }

    override fun visitValue(ctx: ValueContext): Expression {
        return valueExpressionVisitor.visitValue(ctx)
    }

    override fun visitFunctionCall(ctx: FunctionCallContext): Expression {
        return callExpressionVisitor.visitFunctionCall(ctx)
    }

    override fun visitConstructorCall(ctx: ConstructorCallContext): Expression {
        return callExpressionVisitor.visitConstructorCall(ctx)
    }

    override fun visitSupercall(ctx: SupercallContext): Expression {
        return callExpressionVisitor.visitSupercall(ctx)
    }

    override fun visitAdd(ctx: AddContext): Expression {
        return arithmeticExpressionVisitor.visitAdd(ctx)
    }

    override fun visitMultiply(ctx: MultiplyContext): Expression {
        return arithmeticExpressionVisitor.visitMultiply(ctx)
    }

    override fun visitSubstract(ctx: SubstractContext): Expression {
        return arithmeticExpressionVisitor.visitSubstract(ctx)
    }

    override fun visitDivide(ctx: DivideContext): Expression {
        return arithmeticExpressionVisitor.visitDivide(ctx)
    }

    override fun visitConditionalExpression(ctx: ConditionalExpressionContext): ConditionalExpression {
        return conditionalExpressionVisitor.visitConditionalExpression(ctx)
    }

}