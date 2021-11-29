package jiva.domain.node.statement

import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.node.expression.Expression
import java.lang.FunctionalInterface
import jiva.domain.node.statement.VariableDeclaration
import jiva.util.TypeChecker
import jiva.exception.UnsupportedRangedLoopTypes

/**
 * Created by kuba on 23.04.16.
 */
class Assignment : Statement {

    val varName: String
    val expression: Expression

    constructor(varName: String, expression: Expression) {
        this.varName = varName
        this.expression = expression
    }

    constructor(declarationStatement: VariableDeclaration) {
        varName = declarationStatement.name
        expression = declarationStatement.expression
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}