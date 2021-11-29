package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.type.Type

/**
 * Created by kuba on 14.04.16.
 */
class EmptyExpression(override val type: Type) : Expression {

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}