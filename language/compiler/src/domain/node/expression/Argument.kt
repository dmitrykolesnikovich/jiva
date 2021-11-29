package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.type.Type
import java.util.*

/**
 * Created by kuba on 09.05.16.
 */
class Argument(private val expression: Expression, val parameterName: Optional<String>) : Expression {
    override val type: Type
        get() = expression.type

    override fun accept(genrator: ExpressionGenerator) {
        expression.accept(genrator)
    }

    override fun accept(generator: StatementGenerator) {
        expression.accept(generator)
    }
}