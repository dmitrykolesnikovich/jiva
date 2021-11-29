package jiva.parsing.visitor.statement

import jiva.domain.node.statement.Assignment
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.AssignmentContext

class AssignmentStatementVisitor(private val expressionVisitor: ExpressionVisitor) : JivaBaseVisitor<Assignment?>() {

    override fun visitAssignment(ctx: AssignmentContext): Assignment {
        val expressionCtx = ctx.expression()
        val expression = expressionCtx.accept(expressionVisitor)
        val varName = ctx.name().text
        return Assignment(varName, expression!!)
    }

}
