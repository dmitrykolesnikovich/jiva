package jiva.util

import jiva.domain.Function
import jiva.domain.node.expression.Parameter
import jiva.domain.scope.FunctionSignature
import jiva.domain.type.Type

// According to https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.3
object DescriptorFactory {

    fun getMethodDescriptor(function: Function): String {
        val parameters: Collection<Parameter> = function.parameters
        val returnType = function.returnType
        return getMethodDescriptor(parameters, returnType)
    }

    fun getMethodDescriptor(signature: FunctionSignature): String {
        val parameters: Collection<Parameter> = signature.parameters
        val returnType = signature.returnType
        return getMethodDescriptor(parameters, returnType)
    }

    private fun getMethodDescriptor(parameters: Collection<Parameter>, returnType: Type): String {
        val parametersDescriptor = parameters.joinToString("", prefix = "(", postfix = ")") { it.type.descriptor }
        val returnDescriptor = returnType.descriptor
        return parametersDescriptor + returnDescriptor
    }

}