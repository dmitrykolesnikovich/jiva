package jiva.parsing.visitor.statement

import jiva.domain.node.statement.PrintStatement
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.PrintStatementContext

class PrintStatementVisitor(private val visitor: ExpressionVisitor) : JivaBaseVisitor<PrintStatement>() {

    override fun visitPrintStatement(ctx: PrintStatementContext): PrintStatement {
        val expressionCtx = ctx.expression()
        val expression = expressionCtx.accept(visitor)!!
        return PrintStatement(expression)
    }

}