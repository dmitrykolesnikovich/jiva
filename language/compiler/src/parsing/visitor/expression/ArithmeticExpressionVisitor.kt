package jiva.parsing.visitor.expression

import jiva.domain.node.expression.arthimetic.*
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.*

class ArithmeticExpressionVisitor(private val expressionVisitor: ExpressionVisitor) :
    JivaBaseVisitor<ArthimeticExpression>() {

    override fun visitAdd(ctx: AddContext): ArthimeticExpression {
        val leftExpression = ctx.expression(0)
        val rightExpression = ctx.expression(1)
        val leftExpress = leftExpression.accept(expressionVisitor)!!
        val rightExpress = rightExpression.accept(expressionVisitor)!!
        return Addition(leftExpress, rightExpress)
    }

    override fun visitMultiply(ctx: MultiplyContext): ArthimeticExpression {
        val leftExpression = ctx.expression(0)
        val rightExpression = ctx.expression(1)
        val leftExpress = leftExpression.accept(expressionVisitor)!!
        val rightExpress = rightExpression.accept(expressionVisitor)!!
        return Multiplication(leftExpress, rightExpress)
    }

    override fun visitSubstract(ctx: SubstractContext): ArthimeticExpression {
        val leftExpression = ctx.expression(0)
        val rightExpression = ctx.expression(1)
        val leftExpress = leftExpression.accept(expressionVisitor)!!
        val rightExpress = rightExpression.accept(expressionVisitor)!!
        return Substraction(leftExpress, rightExpress)
    }

    override fun visitDivide(ctx: DivideContext): ArthimeticExpression {
        val leftExpression = ctx.expression(0)
        val rightExpression = ctx.expression(1)
        val leftExpress = leftExpression.accept(expressionVisitor)!!
        val rightExpress = rightExpression.accept(expressionVisitor)!!
        return Division(leftExpress, rightExpress)
    }

}
