package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.scope.Field
import jiva.domain.type.Type

/**
 * Created by kuba on 09.04.16.
 */
class FieldReference(field: Field) : Reference {

    override val name: String = field.name
    override val type: Type = field.type
    val ownerInternalName: String = field.ownerInternalName

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}