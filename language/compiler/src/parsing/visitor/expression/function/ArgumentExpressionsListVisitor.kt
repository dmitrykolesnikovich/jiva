package jiva.parsing.visitor.expression.function

import jiva.domain.node.expression.Argument
import jiva.parsing.visitor.expression.ExpressionVisitor
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.*
import org.antlr.v4.runtime.misc.NotNull
import java.util.stream.Collectors

/**
 * Created by kuba on 09.05.16.
 */
class ArgumentExpressionsListVisitor(private val expressionVisitor: ExpressionVisitor) :
    JivaBaseVisitor<List<Argument>>() {

    override fun visitUnnamedArgumentsList(ctx: UnnamedArgumentsListContext): List<Argument> {
        val argumentExpressionVisitor = ArgumentExpressionVisitor(expressionVisitor)
        return ctx.argument().map { a: ArgumentContext -> a.accept(argumentExpressionVisitor) }
    }

    override fun visitNamedArgumentsList(ctx: NamedArgumentsListContext): List<Argument> {
        val argumentExpressionVisitor = ArgumentExpressionVisitor(expressionVisitor)
        return ctx.namedArgument().stream()
            .map { a: NamedArgumentContext -> a.accept(argumentExpressionVisitor) }.collect(Collectors.toList())
    }
}