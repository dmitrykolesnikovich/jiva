package jiva.domain.node.statement

import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.node.expression.Expression

class VariableDeclaration(val name: String, val expression: Expression) : Statement {
    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}