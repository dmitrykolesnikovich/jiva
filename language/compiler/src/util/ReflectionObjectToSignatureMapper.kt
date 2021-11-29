package jiva.util

import jiva.domain.scope.FunctionSignature
import jiva.domain.type.BultInType
import java.lang.reflect.Constructor
import java.lang.reflect.Method
import java.lang.reflect.Parameter

/**
 * Created by kuba on 03.05.16.
 */
object ReflectionObjectToSignatureMapper {

    fun fromMethod(method: Method): FunctionSignature {
        val name = method.name
        val parameters = method.parameters
            .map { p: Parameter ->
                jiva.domain.node.expression.Parameter(
                    p.name,
                    TypeResolver.getFromTypeName(p.type.canonicalName),
                    null
                )
            }
        val returnType = method.returnType
        return FunctionSignature(name, parameters, TypeResolver.getFromTypeName(returnType.canonicalName))
    }

    fun fromConstructor(constructor: Constructor<*>): FunctionSignature {
        val name = constructor.name
        val parameters = constructor.parameters
            .map { p: Parameter ->
                jiva.domain.node.expression.Parameter(
                    p.name,
                    TypeResolver.getFromTypeName(p.type.canonicalName),
                    null
                )
            }
        return FunctionSignature(name, parameters, BultInType.VOID)
    }

}
