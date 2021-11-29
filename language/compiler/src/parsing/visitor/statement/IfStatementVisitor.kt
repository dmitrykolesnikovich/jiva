package jiva.parsing.visitor.statement

import jiva.domain.node.statement.IfStatement
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.IfStatementContext

class IfStatementVisitor(
    private val statementVisitor: StatementVisitor,
    private val expressionVisitor: ExpressionVisitor
) : JivaBaseVisitor<IfStatement>() {

    override fun visitIfStatement(ctx: IfStatementContext): IfStatement {
        val conditionalExpressionContext = ctx.expression()
        val condition = conditionalExpressionContext.accept(expressionVisitor)!!
        val trueStatement = ctx.trueStatement.accept(statementVisitor)!!
        if (ctx.falseStatement != null) {
            val falseStatement = ctx.falseStatement.accept(statementVisitor)!!
            return IfStatement(condition, trueStatement, falseStatement)
        }
        return IfStatement(condition, trueStatement)
    }

}