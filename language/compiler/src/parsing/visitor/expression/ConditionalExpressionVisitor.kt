package jiva.parsing.visitor.expression

import jiva.domain.CompareSign
import jiva.domain.node.expression.ConditionalExpression
import jiva.domain.node.expression.Value
import jiva.domain.type.BultInType
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.ConditionalExpressionContext

class ConditionalExpressionVisitor(private val visitor: ExpressionVisitor) : JivaBaseVisitor<ConditionalExpression>() {

    override fun visitConditionalExpression(ctx: ConditionalExpressionContext): ConditionalExpression {
        val leftContext = ctx.expression(0)
        val rightContext = ctx.expression(1)
        val leftExpression = leftContext.accept(visitor)
        val rightExpression = if (rightContext != null) rightContext.accept(visitor) else Value(BultInType.INT, "0")
        val cmpSign = if (ctx.cmp != null) CompareSign.fromString(ctx.cmp.text) else CompareSign.NOT_EQUAL
        return ConditionalExpression(leftExpression!!, rightExpression!!, cmpSign)
    }

}