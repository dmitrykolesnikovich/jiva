package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.type.Type

/**
 * Created by kuba on 02.04.16.
 */
class Value(override val type: Type, val value: String) : Expression {

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}