package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator

/**
 * Created by kuba on 13.05.16.
 */
interface Reference : Expression {
    val name: String
    override fun accept(generator: ExpressionGenerator)
    override fun accept(generator: StatementGenerator)
}