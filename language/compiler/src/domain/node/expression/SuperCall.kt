package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.type.BultInType
import jiva.domain.type.Type

/**
 * Created by kuba on 05.05.16.
 */
class SuperCall(override val arguments: List<Argument> = emptyList()) : Call {

    override val identifier: String = "super"
    override val type: Type = BultInType.VOID

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}