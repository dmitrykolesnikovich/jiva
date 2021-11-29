package jiva.util

import jiva.domain.scope.FunctionSignature
import jiva.domain.type.BultInType
import java.lang.reflect.Constructor
import java.lang.reflect.Method

/**
 * Created by kuba on 03.05.16.
 */
object ReflectionObjectToSignatureMapper {

    fun fromMethod(method: Method): FunctionSignature {
        val name = method.name
        val parameters = method.parameters
            .map {
                jiva.domain.node.expression.Parameter(
                    it.name,
                    TypeResolver.getFromTypeName(it.type.canonicalName),
                    null
                )
            }
        val returnType = method.returnType
        return FunctionSignature(name, parameters, TypeResolver.getFromTypeName(returnType.canonicalName))
    }

    fun fromConstructor(constructor: Constructor<*>): FunctionSignature {
        val name = constructor.name
        val parameters = constructor.parameters
            .map {
                jiva.domain.node.expression.Parameter(
                    it.name,
                    TypeResolver.getFromTypeName(it.type.canonicalName),
                    null
                )
            }
        return FunctionSignature(name, parameters, BultInType.VOID)
    }

}
