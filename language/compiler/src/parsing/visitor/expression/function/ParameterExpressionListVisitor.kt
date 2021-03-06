package jiva.parsing.visitor.expression.function

import jiva.domain.node.expression.Parameter
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.ParametersListContext
import jiva.parsing.visitor.expression.ExpressionVisitor

/**
 * Created by kuba on 09.05.16.
 */
class ParameterExpressionListVisitor(private val visitor: ExpressionVisitor) : JivaBaseVisitor<List<Parameter>>() {

    override fun visitParametersList(ctx: ParametersListContext): List<Parameter> {
        val paramsCtx = ctx.parameter()
        val parameterExpressionVisitor = ParameterExpressionVisitor(visitor)
        val parameters: MutableList<Parameter> = ArrayList()
        if (paramsCtx != null) {
            val params = paramsCtx.map { it.accept(parameterExpressionVisitor)!! }
            parameters.addAll(params)
        }
        val paramsWithDefaultValueCtx = ctx.parameterWithDefaultValue()
        if (paramsWithDefaultValueCtx != null) {
            val params = paramsWithDefaultValueCtx.map { it.accept(parameterExpressionVisitor)!! }
            parameters.addAll(params)
        }
        return parameters
    }

}
