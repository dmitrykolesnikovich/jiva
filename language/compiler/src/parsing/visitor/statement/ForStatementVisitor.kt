package jiva.parsing.visitor.statement

import jiva.domain.node.statement.Assignment
import jiva.domain.node.statement.RangedForStatement
import jiva.domain.node.statement.Statement
import jiva.domain.node.statement.VariableDeclaration
import jiva.domain.scope.LocalVariable
import jiva.domain.scope.Scope
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.ForStatementContext

class ForStatementVisitor(private val scope: Scope) : JivaBaseVisitor<RangedForStatement?>() {

    private val visitor: ExpressionVisitor = ExpressionVisitor(scope)

    override fun visitForStatement(ctx: ForStatementContext): RangedForStatement {
        val newScope = Scope(scope)
        val forExpressionContext = ctx.forConditions()
        val startExpression = forExpressionContext.startExpr.accept(visitor)!!
        val endExpression = forExpressionContext.endExpr.accept(visitor)!!
        val iterator = forExpressionContext.iterator
        val statementVisitor = StatementVisitor(newScope)
        val varName = iterator.text
        return if (newScope.isLocalVariableExists(varName)) {
            val iteratorVariable: Statement = Assignment(varName, startExpression)
            val statement = ctx.statement().accept(statementVisitor)!!
            RangedForStatement(iteratorVariable, startExpression, endExpression, statement, varName, newScope)
        } else {
            newScope.addLocalVariable(LocalVariable(varName, startExpression.type))
            val iteratorVariable: Statement = VariableDeclaration(varName, startExpression)
            val statement = ctx.statement().accept(statementVisitor)!!
            RangedForStatement(iteratorVariable, startExpression, endExpression, statement, varName, newScope)
        }
    }

}