package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.scope.Field
import jiva.domain.type.Type

/**
 * Created by kuba on 09.04.16.
 */
class FieldReference(private val field: Field) : Reference {

    override val type: Type = field.type
    val ownerInternalName: String = field.ownerInternalName

    override fun geName(): String {
        return field.name
    }

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}