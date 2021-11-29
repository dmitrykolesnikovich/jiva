package jiva.domain.scope

import jiva.bytecodegeneration.FieldGenerator
import jiva.domain.type.Type

class Field(override val name: String, owner: Type, override val type: Type) : Variable {

    val ownerInternalName: String = owner.internalName

    fun accept(generator: FieldGenerator) {
        generator.generate(this)
    }

}
