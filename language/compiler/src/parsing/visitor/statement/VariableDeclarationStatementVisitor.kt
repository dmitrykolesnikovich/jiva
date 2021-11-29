package jiva.parsing.visitor.statement

import jiva.domain.node.statement.VariableDeclaration
import jiva.domain.scope.LocalVariable
import jiva.domain.scope.Scope
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.VariableDeclarationContext

class VariableDeclarationStatementVisitor(private val expressionVisitor: ExpressionVisitor, private val scope: Scope) :
    JivaBaseVisitor<VariableDeclaration>() {

    override fun visitVariableDeclaration(ctx: VariableDeclarationContext): VariableDeclaration {
        val varName = ctx.name().text
        val expressionCtx = ctx.expression()
        val expression = expressionCtx.accept(expressionVisitor)!!
        scope.addLocalVariable(LocalVariable(varName, expression.type))
        return VariableDeclaration(varName, expression)
    }

}
