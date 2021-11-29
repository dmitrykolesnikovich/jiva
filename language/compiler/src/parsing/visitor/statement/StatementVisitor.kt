package jiva.parsing.visitor.statement

import jiva.domain.node.expression.ConditionalExpression
import jiva.domain.node.expression.Expression
import jiva.domain.node.statement.Statement
import jiva.domain.scope.Scope
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.*
import org.antlr.v4.runtime.misc.NotNull

/**
 * Created by kuba on 01.04.16.
 */
class StatementVisitor(scope: Scope) : JivaBaseVisitor<Statement?>() {

    private val expressionVisitor = ExpressionVisitor(scope)
    private val printStatementVisitor = PrintStatementVisitor(expressionVisitor)
    private val variableDeclarationStatementVisitor = VariableDeclarationStatementVisitor(expressionVisitor, scope)
    private val returnStatementVisitor = ReturnStatementVisitor(expressionVisitor)
    private val blockStatementVisitor = BlockStatementVisitor(scope)
    private val ifStatementVisitor = IfStatementVisitor(this, expressionVisitor)
    private val forStatementVisitor = ForStatementVisitor(scope)
    private val assignmentStatementVisitor = AssignmentStatementVisitor(expressionVisitor)

    override fun visitPrintStatement(ctx: PrintStatementContext): Statement {
        return printStatementVisitor.visitPrintStatement(ctx)
    }

    override fun visitVariableDeclaration(ctx: VariableDeclarationContext): Statement {
        return variableDeclarationStatementVisitor.visitVariableDeclaration(ctx)
    }

    override fun visitReturnVoid(ctx: ReturnVoidContext): Statement {
        return returnStatementVisitor.visitReturnVoid(ctx)
    }

    override fun visitReturnWithValue(ctx: ReturnWithValueContext): Statement {
        return returnStatementVisitor.visitReturnWithValue(ctx)
    }

    override fun visitBlock(ctx: BlockContext): Statement {
        return blockStatementVisitor.visitBlock(ctx)
    }

    override fun visitIfStatement(ctx: IfStatementContext): Statement {
        return ifStatementVisitor.visitIfStatement(ctx)
    }

    override fun visitVarReference(ctx: VarReferenceContext): Expression {
        return expressionVisitor.visitVarReference(ctx)
    }

    override fun visitValue(ctx: ValueContext): Expression {
        return expressionVisitor.visitValue(ctx)
    }

    override fun visitFunctionCall(ctx: FunctionCallContext): Expression {
        return expressionVisitor.visitFunctionCall(ctx)
    }

    override fun visitConstructorCall(ctx: ConstructorCallContext): Expression {
        return expressionVisitor.visitConstructorCall(ctx)
    }

    override fun visitSupercall(ctx: SupercallContext): Expression {
        return expressionVisitor.visitSupercall(ctx)
    }

    override fun visitAdd(ctx: AddContext): Expression {
        return expressionVisitor.visitAdd(ctx)
    }

    override fun visitMultiply(ctx: MultiplyContext): Expression {
        return expressionVisitor.visitMultiply(ctx)
    }

    override fun visitSubstract(ctx: SubstractContext): Expression {
        return expressionVisitor.visitSubstract(ctx)
    }

    override fun visitDivide(ctx: DivideContext): Expression {
        return expressionVisitor.visitDivide(ctx)
    }

    override fun visitConditionalExpression(ctx: ConditionalExpressionContext): ConditionalExpression {
        return expressionVisitor.visitConditionalExpression(ctx)
    }

    override fun visitForStatement(ctx: ForStatementContext): Statement {
        return forStatementVisitor.visitForStatement(ctx)
    }

    override fun visitAssignment(ctx: AssignmentContext): Statement {
        return assignmentStatementVisitor.visitAssignment(ctx)
    }

}