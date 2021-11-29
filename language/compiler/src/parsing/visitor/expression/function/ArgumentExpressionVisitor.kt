package jiva.parsing.visitor.expression.function

import jiva.domain.node.expression.Argument
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.ArgumentContext
import jiva.parser.JivaParser.NamedArgumentContext
import org.antlr.v4.runtime.misc.NotNull
import java.util.*

/**
 * Created by kuba on 09.05.16.
 */
class ArgumentExpressionVisitor(private val expressionVisitor: ExpressionVisitor) : JivaBaseVisitor<Argument>() {
    override fun visitArgument(@NotNull ctx: ArgumentContext): Argument {
        val value = ctx.expression().accept(expressionVisitor)!!
        return Argument(value, Optional.empty())
    }

    override fun visitNamedArgument(@NotNull ctx: NamedArgumentContext): Argument {
        val value = ctx.expression().accept(expressionVisitor)!!
        val name = ctx.name().text
        return Argument(value, Optional.of(name))
    }
}