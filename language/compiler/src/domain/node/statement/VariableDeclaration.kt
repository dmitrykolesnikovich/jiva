package jiva.domain.node.statement

import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.node.expression.Expression

/**
 * Created by kuba on 28.03.16.
 */
class VariableDeclaration(val name: String, val expression: Expression) : Statement {
    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}