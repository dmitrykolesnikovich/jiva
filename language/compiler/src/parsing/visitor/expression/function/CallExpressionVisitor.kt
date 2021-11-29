package jiva.parsing.visitor.expression.function

import jiva.domain.node.expression.*
import jiva.domain.scope.LocalVariable
import jiva.domain.scope.Scope
import jiva.domain.type.ClassType
import jiva.exception.FunctionNameEqualClassException
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.*
import org.antlr.v4.runtime.misc.NotNull
import java.util.*

class CallExpressionVisitor(private val expressionVisitor: ExpressionVisitor, private val scope: Scope): JivaBaseVisitor<Call>() {
    
    override fun visitFunctionCall(ctx: FunctionCallContext): Call {
        val functionName = ctx.functionName().text
        if (functionName == scope.className) {
            throw FunctionNameEqualClassException(functionName)
        }
        val arguments = getArgumentsForCall(ctx.argumentList())
        val ownerIsExplicit = ctx.owner != null
        if (ownerIsExplicit) {
            val owner = ctx.owner.accept(expressionVisitor)!!
            val signature = scope.getMethodCallSignature(Optional.of(owner.type), functionName, arguments)
            return FunctionCall(signature, arguments, owner)
        }
        val thisType = ClassType(scope.className)
        val signature = scope.getMethodCallSignature(functionName, arguments)
        val thisVariable = LocalVariable("this", thisType)
        return FunctionCall(signature, arguments, LocalVariableReference(thisVariable))
    }

    override fun visitConstructorCall(ctx: ConstructorCallContext): Call {
        val className = ctx.className().text
        val arguments = getArgumentsForCall(ctx.argumentList())
        return ConstructorCall(className, arguments)
    }

    override fun visitSupercall(ctx: SupercallContext): Call {
        val arguments = getArgumentsForCall(ctx.argumentList())
        return SuperCall(arguments)
    }

    private fun getArgumentsForCall(argumentsListCtx: ArgumentListContext?): List<Argument> {
        if (argumentsListCtx != null) {
            val visitor = ArgumentExpressionsListVisitor(expressionVisitor)
            return argumentsListCtx.accept(visitor)
        }
        return emptyList()
    }
}