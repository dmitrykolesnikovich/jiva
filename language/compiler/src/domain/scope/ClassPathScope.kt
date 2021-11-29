package jiva.domain.scope

import jiva.domain.type.Type
import jiva.util.ReflectionObjectToSignatureMapper
import org.apache.commons.lang3.reflect.ConstructorUtils
import org.apache.commons.lang3.reflect.MethodUtils

class ClassPathScope {

    fun getMethodSignature(owner: Type, methodName: String?, arguments: List<Type>): FunctionSignature? {
        return try {
            val methodOwnerClass = owner.typeClass
            val params = arguments.map { it.typeClass }.toTypedArray()
            val method = MethodUtils.getMatchingAccessibleMethod(methodOwnerClass, methodName, *params)
            ReflectionObjectToSignatureMapper.fromMethod(method)
        } catch (e: Exception) {
            null
        }
    }

    fun getConstructorSignature(className: String?, arguments: List<Type>): FunctionSignature? {
        return try {
            val methodOwnerClass = Class.forName(className)
            val params = arguments.map { it.typeClass }.toTypedArray()
            val constructor = ConstructorUtils.getMatchingAccessibleConstructor(methodOwnerClass, *params)
            ReflectionObjectToSignatureMapper.fromConstructor(constructor)
        } catch (e: Exception) {
            null
        }
    }

}