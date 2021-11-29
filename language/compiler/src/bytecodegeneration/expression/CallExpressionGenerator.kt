package jiva.bytecodegeneration.expression

import com.google.common.collect.Ordering
import jiva.domain.node.expression.*
import jiva.domain.scope.FunctionSignature
import jiva.domain.scope.Scope
import jiva.domain.type.ClassType
import jiva.exception.BadArgumentsToFunctionCallException
import jiva.exception.WrongArgumentNameException
import jiva.util.DescriptorFactory
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import java.util.*
import java.util.function.Consumer

class CallExpressionGenerator(
    val expressionGenerator: ExpressionGenerator,
    val scope: Scope,
    val methodVisitor: MethodVisitor
) {

    fun generate(constructorCall: ConstructorCall) {
        val signature = scope.getConstructorCallSignature(constructorCall.identifier, constructorCall.arguments)
        val ownerDescriptor = ClassType(signature.name).descriptor
        methodVisitor.visitTypeInsn(Opcodes.NEW, ownerDescriptor)
        methodVisitor.visitInsn(Opcodes.DUP)
        val methodDescriptor = DescriptorFactory.getMethodDescriptor(signature)
        generateArguments(constructorCall, signature)
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, ownerDescriptor, "<init>", methodDescriptor, false)
    }

    fun generate(superCall: SuperCall) {
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
        generateArguments(superCall)
        val ownerDescriptor = scope.superClassInternalName
        methodVisitor.visitMethodInsn(
            Opcodes.INVOKESPECIAL,
            ownerDescriptor,
            "<init>",
            "()V" /*TODO Handle super calls with arguments*/,
            false
        )
    }

    fun generate(functionCall: FunctionCall) {
        val owner = functionCall.owner
        owner.accept(expressionGenerator)
        generateArguments(functionCall)
        val functionName = functionCall.identifier
        val methodDescriptor = DescriptorFactory.getMethodDescriptor(functionCall.signature)
        val ownerDescriptor = functionCall.ownerType.internalName
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ownerDescriptor, functionName, methodDescriptor, false)
    }

    /*internals*/

    private fun generateArguments(call: FunctionCall) {
        val signature = scope.getMethodCallSignature(Optional.of(call.ownerType), call.identifier, call.arguments)
        generateArguments(call, signature)
    }

    private fun generateArguments(call: SuperCall) {
        val signature = scope.getMethodCallSignature(call.identifier, call.arguments)
        generateArguments(call, signature)
    }

    private fun generateArguments(call: ConstructorCall) {
        val signature = scope.getConstructorCallSignature(call.identifier, call.arguments)
        generateArguments(call, signature)
    }

    private fun generateArguments(call: Call, signature: FunctionSignature) {
        val parameters = signature.parameters
        var arguments = call.arguments
        if (arguments.size > parameters.size) {
            throw BadArgumentsToFunctionCallException(call)
        }
        arguments = getSortedArguments(arguments, parameters)
        arguments.forEach(Consumer { argument: Argument ->
            argument.accept(expressionGenerator)
        })
        generateDefaultParameters(call, parameters, arguments)
    }

    private fun getSortedArguments(arguments: List<Argument>, parameters: List<Parameter>): List<Argument> {
        val argumentIndexComparator = Comparator { o1: Argument, o2: Argument ->
            if (o1.parameterName != null) {
                getIndexOfArgument(o1, parameters) - getIndexOfArgument(o2, parameters)
            } else {
                0
            }
        }
        return Ordering.from(argumentIndexComparator).immutableSortedCopy(arguments)
    }

    private fun getIndexOfArgument(argument: Argument, parameters: List<Parameter>): Int {
        return parameters.stream()
            .filter { p: Parameter -> p.name == argument.parameterName }
            .map { o: Parameter -> parameters.indexOf(o) }
            .findFirst()
            .orElseThrow { WrongArgumentNameException(argument, parameters) }
    }

    private fun generateDefaultParameters(call: Call, parameters: List<Parameter>, arguments: List<Argument>) {
        for (index in arguments.size until parameters.size) {
            val defaultParameter = parameters[index].defaultValue ?: throw BadArgumentsToFunctionCallException(call)
            defaultParameter.accept(expressionGenerator)
        }
    }

}