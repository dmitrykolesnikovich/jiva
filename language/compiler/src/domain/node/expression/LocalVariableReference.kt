package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.scope.LocalVariable
import jiva.domain.type.Type

/**
 * Created by kuba on 09.04.16.
 */
class LocalVariableReference(variable: LocalVariable) : Reference {

    override val name: String = variable.name
    override val type: Type = variable.type

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}
