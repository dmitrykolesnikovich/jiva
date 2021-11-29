package jiva.domain

import jiva.bytecodegeneration.MethodGenerator
import jiva.domain.node.statement.Statement
import jiva.domain.scope.FunctionSignature
import jiva.domain.type.BultInType
import jiva.domain.type.Type

/**
 * Created by kuba on 07.05.16.
 */
class Constructor(signature: FunctionSignature, block: Statement) : Function(signature, block) {

    override val returnType: Type = BultInType.VOID

    override fun accept(generator: MethodGenerator) {
        generator.generate(this)
    }

}