package jiva.parsing.visitor.expression.function

import jiva.domain.node.expression.Argument
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.ArgumentContext
import jiva.parser.JivaParser.NamedArgumentContext

/**
 * Created by kuba on 09.05.16.
 */
class ArgumentExpressionVisitor(private val visitor: ExpressionVisitor) : JivaBaseVisitor<Argument>() {

    override fun visitArgument(ctx: ArgumentContext): Argument {
        val value = ctx.expression().accept(visitor)!!
        return Argument(value, null)
    }

    override fun visitNamedArgument(ctx: NamedArgumentContext): Argument {
        val value = ctx.expression().accept(visitor)!!
        val name = ctx.name().text
        return Argument(value, name)
    }

}
