package jiva.domain.scope

import jiva.domain.node.expression.Argument
import jiva.domain.node.expression.Parameter
import jiva.domain.type.Type
import jiva.exception.ParameterForNameNotFoundException
import jiva.util.allIndexed

/**
 * Created by kuba on 06.04.16.
 */
class FunctionSignature(val name: String, val parameters: List<Parameter>, val returnType: Type) {

    fun getParameterForName(name: String): Parameter {
        return parameters.stream()
            .filter { param: Parameter -> param.name == name }
            .findFirst()
            .orElseThrow { ParameterForNameNotFoundException(name, parameters) }
    }

    fun getIndexOfParameter(parameterName: String): Int {
        val parameter = getParameterForName(parameterName)
        return parameters.indexOf(parameter)
    }

    fun matches(otherSignatureName: String?, arguments: List<Argument>): Boolean {
        val namesAreEqual = name == otherSignatureName
        if (!namesAreEqual) return false
        val nonDefaultParametersCount = parameters.stream()
            .filter { p: Parameter -> p.defaultValue == null }
            .count()
        if (nonDefaultParametersCount > arguments.size) return false
        val isNamedArgList = arguments.stream().anyMatch { a: Argument -> a.parameterName.isPresent }
        return if (isNamedArgList) {
            areArgumentsAndParamsMatchedByName(arguments)
        } else areArgumentsAndParamsMatchedByIndex(arguments)
    }

    private fun areArgumentsAndParamsMatchedByIndex(arguments: List<Argument>): Boolean {
        return arguments.allIndexed { index, argument ->
            val argumentType = argument.type
            val parameterType = parameters[index].type
            argumentType == parameterType
        }
    }

    private fun areArgumentsAndParamsMatchedByName(arguments: List<Argument>): Boolean {
        return arguments.all { argument ->
            val paramName = argument.parameterName.get()
            parameters.stream().map(Parameter::name).anyMatch { anObject: String? -> paramName == anObject }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as FunctionSignature
        if (name != that.name) return false
        return if (parameters != that.parameters) false else !(returnType != that.returnType)
    }

    override fun hashCode(): Int {
        var result = name.hashCode() ?: 0
        result = 31 * result + (parameters.hashCode() ?: 0)
        result = 31 * result + (returnType.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "FunctionSignature{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", returnType=" + returnType +
                '}'
    }
    
}