package jiva.parsing.visitor.statement

import jiva.domain.node.expression.EmptyExpression
import jiva.domain.node.statement.ReturnStatement
import jiva.domain.type.BultInType
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.ReturnVoidContext
import jiva.parser.JivaParser.ReturnWithValueContext
import org.antlr.v4.runtime.misc.NotNull

class ReturnStatementVisitor(private val expressionVisitor: ExpressionVisitor) : JivaBaseVisitor<ReturnStatement?>() {

    override fun visitReturnVoid(ctx: ReturnVoidContext): ReturnStatement {
        return ReturnStatement(EmptyExpression(BultInType.VOID))
    }

    override fun visitReturnWithValue(ctx: ReturnWithValueContext): ReturnStatement {
        val expression = ctx.expression().accept(expressionVisitor)!!
        return ReturnStatement(expression)
    }
}