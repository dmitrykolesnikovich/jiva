package jiva.parsing.visitor.expression

import jiva.domain.node.expression.Value
import jiva.util.TypeResolver
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.ValueContext

class ValueExpressionVisitor : JivaBaseVisitor<Value>() {

    override fun visitValue(ctx: ValueContext): Value {
        val value = ctx.text
        val type = TypeResolver.getFromValue(ctx)
        return Value(type, value)
    }

}
