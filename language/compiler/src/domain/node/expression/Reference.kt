package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator

/**
 * Created by kuba on 13.05.16.
 */
interface Reference : Expression {
    fun geName(): String
    override fun accept(genrator: ExpressionGenerator)
    override fun accept(generator: StatementGenerator)
}