package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.type.Type

/**
 * Created by kuba on 09.05.16.
 */
class Argument(private val expression: Expression, val parameterName: String?) : Expression {

    override val type: Type = expression.type

    override fun accept(generator: ExpressionGenerator) {
        expression.accept(generator)
    }

    override fun accept(generator: StatementGenerator) {
        expression.accept(generator)
    }

}