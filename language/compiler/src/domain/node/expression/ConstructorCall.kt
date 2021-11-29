package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.type.ClassType
import jiva.domain.type.Type

/**
 * Created by kuba on 05.05.16.
 */
class ConstructorCall(override val type: Type, override val arguments: List<Argument>) : Call {

    override val identifier: String = type.name

    constructor(identifier: String, arguments: List<Argument> = emptyList()) : this(ClassType(identifier), arguments)

    override fun accept(genrator: ExpressionGenerator) {
        genrator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}