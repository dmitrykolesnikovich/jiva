package jiva.bytecodegeneration.statement

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.domain.node.statement.Assignment
import jiva.domain.node.statement.VariableDeclaration

class VariableDeclarationStatementGenerator(val statementGenerator: StatementGenerator, val expressionGenerator: ExpressionGenerator) {

    fun generate(variableDeclaration: VariableDeclaration) {
        val expression = variableDeclaration.expression
        expression.accept(expressionGenerator)
        val assignment = Assignment(variableDeclaration)
        assignment.accept(statementGenerator)
    }

}