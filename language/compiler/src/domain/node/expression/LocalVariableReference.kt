package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.scope.LocalVariable
import jiva.domain.type.Type

/**
 * Created by kuba on 09.04.16.
 */
class LocalVariableReference(private val variable: LocalVariable) : Reference {

    override fun geName(): String {
        return variable.name
    }

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override val type: Type
        get() = variable.type

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}