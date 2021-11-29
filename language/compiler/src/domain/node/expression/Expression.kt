package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.node.statement.Statement
import jiva.domain.type.Type

/**
 * Created by kuba on 02.04.16.
 */
interface Expression : Statement {
    val type: Type
    fun accept(genrator: ExpressionGenerator)
    override fun accept(generator: StatementGenerator)
}