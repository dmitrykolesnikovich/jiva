package com.kubadziworski.parsing.visitor.expression.function;

import jiva.parser.JivaBaseVisitor;
import jiva.parser.JivaParser;
import jiva.parser.JivaParser.ArgumentContext;
import jiva.parser.JivaParser.NamedArgumentContext;
import com.kubadziworski.domain.node.expression.Argument;
import com.kubadziworski.domain.node.expression.Expression;
import com.kubadziworski.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Optional;

/**
 * Created by kuba on 09.05.16.
 */
public class ArgumentExpressionVisitor extends JivaBaseVisitor<Argument> {

    private final ExpressionVisitor expressionVisitor;

    public ArgumentExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public Argument visitArgument(@NotNull ArgumentContext ctx) {
        Expression value = ctx.expression().accept(expressionVisitor);
        return new Argument(value, Optional.empty());
    }

    @Override
    public Argument visitNamedArgument(@NotNull NamedArgumentContext ctx) {
        Expression value = ctx.expression().accept(expressionVisitor);
        String name = ctx.name().getText();
        return new Argument(value, Optional.of(name));
    }

}
