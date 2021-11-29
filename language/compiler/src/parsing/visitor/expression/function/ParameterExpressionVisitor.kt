package jiva.parsing.visitor.expression.function

import jiva.domain.node.expression.Parameter
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.util.TypeResolver
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.ParameterContext
import jiva.parser.JivaParser.ParameterWithDefaultValueContext

/**
 * Created by kuba on 09.05.16.
 */
class ParameterExpressionVisitor(private val visitor: ExpressionVisitor) : JivaBaseVisitor<Parameter?>() {

    override fun visitParameter(ctx: ParameterContext): Parameter {
        val name = ctx.ID().text
        val type = TypeResolver.getFromTypeContext(ctx.type())
        return Parameter(name, type, null)
    }

    override fun visitParameterWithDefaultValue(ctx: ParameterWithDefaultValueContext): Parameter {
        val name = ctx.ID().text
        val type = TypeResolver.getFromTypeContext(ctx.type())
        val defaultValue = ctx.defaultValue.accept(visitor)
        return Parameter(name, type, defaultValue)
    }

}
