package jiva.domain.scope

import jiva.bytecodegeneration.FieldGenerator
import jiva.domain.type.Type

/**
 * Created by kuba on 13.05.16.
 */
class Field(override val name: String, private val owner: Type, override val type: Type) : Variable {

    val ownerInternalName: String = owner.internalName

    fun accept(generator: FieldGenerator) {
        generator.generate(this)
    }

}
