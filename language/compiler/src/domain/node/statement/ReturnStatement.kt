package jiva.domain.node.statement

import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.node.expression.Expression

/**
 * Created by kuba on 11.04.16.
 */
class ReturnStatement(val expression: Expression) : Statement {

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}
