package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.scope.FunctionSignature
import jiva.domain.type.Type

class FunctionCall(val signature: FunctionSignature, override val arguments: List<Argument>, val owner: Expression) : Call {

    override val type: Type = signature.returnType
    override val identifier: String = signature.name
    val ownerType: Type = owner.type

    constructor(signature: FunctionSignature, arguments: List<Argument>, ownerType: Type) :
            this(signature, arguments, EmptyExpression(ownerType))


    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}
