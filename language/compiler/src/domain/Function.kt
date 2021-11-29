package jiva.domain

import jiva.bytecodegeneration.MethodGenerator
import jiva.domain.node.expression.Parameter
import jiva.domain.node.statement.Statement
import jiva.domain.scope.FunctionSignature
import jiva.domain.type.Type

/**
 * Created by kuba on 28.03.16.
 */
open class Function(private val functionSignature: FunctionSignature, val rootStatement: Statement) {

    val name: String get() = functionSignature.name
    val parameters: List<Parameter> get() = functionSignature.parameters
    open val returnType: Type get() = functionSignature.returnType

    open fun accept(generator: MethodGenerator) {
        generator.generate(this)
    }

}