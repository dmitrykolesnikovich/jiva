package jiva.parsing.visitor

import jiva.domain.scope.FunctionSignature
import jiva.domain.scope.Scope
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parsing.visitor.expression.function.ParameterExpressionListVisitor
import jiva.util.TypeResolver
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.FunctionDeclarationContext

class FunctionSignatureVisitor(scope: Scope) : JivaBaseVisitor<FunctionSignature?>() {

    private val expressionVisitor: ExpressionVisitor = ExpressionVisitor(scope)

    override fun visitFunctionDeclaration(ctx: FunctionDeclarationContext): FunctionSignature {
        val functionName = ctx.functionName().text
        val returnType = TypeResolver.getFromTypeContext(ctx.type())
        val parametersCtx = ctx.parametersList()
        if (parametersCtx != null) {
            val parameters = parametersCtx.accept(ParameterExpressionListVisitor(expressionVisitor))
            return FunctionSignature(functionName, parameters, returnType)
        }
        return FunctionSignature(functionName, emptyList(), returnType)
    }

}