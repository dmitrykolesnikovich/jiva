package jiva.domain.scope

import com.google.common.collect.Lists
import jiva.domain.MetaData
import jiva.domain.node.expression.Argument
import jiva.domain.type.BultInType
import jiva.domain.type.ClassType
import jiva.domain.type.Type
import jiva.exception.FieldNotFoundException
import jiva.exception.LocalVariableNotFoundException
import jiva.exception.MethodSignatureNotFoundException
import jiva.exception.MethodWithNameAlreadyDefinedException
import org.apache.commons.collections4.map.LinkedMap
import java.util.*
import java.util.stream.Collectors

/**
 * Created by kuba on 02.04.16.
 */
class Scope {

    private val functionSignatures: MutableList<FunctionSignature>
    private val metaData: MetaData
    private val localVariables: LinkedMap<String, LocalVariable>
    private val fields: MutableMap<String?, Field>
    private val superClassName: String get() = metaData.superClassName
    val className: String get() = metaData.className
    val superClassInternalName: String get() = ClassType(superClassName).internalName
    val classType: Type get() = ClassType(className)
    val classInternalName: String get() = classType.internalName

    constructor(metaData: MetaData) {
        this.metaData = metaData
        functionSignatures = ArrayList()
        localVariables = LinkedMap()
        fields = LinkedMap()
    }

    constructor(scope: Scope) {
        metaData = scope.metaData
        functionSignatures = Lists.newArrayList(scope.functionSignatures)
        fields = LinkedMap(scope.fields)
        localVariables = LinkedMap(scope.localVariables)
    }

    fun addSignature(signature: FunctionSignature) {
        if (isParameterLessSignatureExists(signature.name)) {
            throw MethodWithNameAlreadyDefinedException(signature)
        }
        functionSignatures.add(signature)
    }

    fun isParameterLessSignatureExists(identifier: String?): Boolean {
        return isSignatureExists(identifier, emptyList())
    }

    fun isSignatureExists(identifier: String?, arguments: List<Argument>): Boolean {
        return if (identifier == "super") true else functionSignatures.stream()
            .anyMatch { signature: FunctionSignature -> signature.matches(identifier, arguments) }
    }

    fun getMethodCallSignatureWithoutParameters(identifier: String): FunctionSignature {
        return getMethodCallSignature(identifier, emptyList())
    }

    fun getConstructorCallSignature(className: String, arguments: List<Argument>): FunctionSignature {
        val isDifferentThanCurrentClass = className != className
        if (isDifferentThanCurrentClass) {
            val argumentsTypes = arguments.stream().map(Argument::type).collect(Collectors.toList())
            return ClassPathScope().getConstructorSignature(className, argumentsTypes)
                ?: throw MethodSignatureNotFoundException(this, className, arguments)
        }
        return getConstructorCallSignatureForCurrentClass(arguments)
    }

    private fun getConstructorCallSignatureForCurrentClass(arguments: List<Argument>): FunctionSignature {
        return getMethodCallSignature(Optional.empty(), className, arguments)
    }

    fun getMethodCallSignature(
        owner: Optional<Type>,
        methodName: String,
        arguments: List<Argument>
    ): FunctionSignature {
        val isDifferentThanCurrentClass = owner.isPresent && owner.get() != classType
        if (isDifferentThanCurrentClass) {
            val argumentsTypes = arguments.stream().map(Argument::type).collect(Collectors.toList())
            return ClassPathScope().getMethodSignature(owner.get(), methodName, argumentsTypes)
                ?: throw MethodSignatureNotFoundException(this, methodName, arguments)
        }
        return getMethodCallSignature(methodName, arguments)
    }

    fun getMethodCallSignature(identifier: String, arguments: List<Argument>): FunctionSignature {
        return if (identifier == "super") {
            FunctionSignature("super", emptyList(), BultInType.VOID)
        } else functionSignatures.firstOrNull { it.matches(identifier, arguments) }
            ?: throw MethodSignatureNotFoundException(this, identifier, arguments)
    }

    fun addLocalVariable(variable: LocalVariable) {
        localVariables[variable.name] = variable
    }

    fun getLocalVariable(varName: String): LocalVariable {
        return localVariables[varName] ?: throw LocalVariableNotFoundException(this, varName)
    }

    fun getLocalVariableIndex(varName: String?): Int {
        return localVariables.indexOf(varName)
    }

    fun isLocalVariableExists(varName: String?): Boolean {
        return localVariables.containsKey(varName)
    }

    fun addField(field: Field) {
        fields[field.name] = field
    }

    fun getField(fieldName: String): Field {
        return Optional.ofNullable(fields[fieldName])
            .orElseThrow { FieldNotFoundException(this, fieldName) }
    }

    fun isFieldExists(varName: String?): Boolean {
        return fields.containsKey(varName)
    }

}