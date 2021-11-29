package com.kubadziworski.parsing.visitor.expression;

import jiva.parser.JivaBaseVisitor;
import jiva.parser.JivaParser;
import jiva.parser.JivaParser.ValueContext;
import com.kubadziworski.domain.node.expression.Value;
import com.kubadziworski.domain.type.Type;
import com.kubadziworski.util.TypeResolver;
import org.antlr.v4.runtime.misc.NotNull;

public class ValueExpressionVisitor extends JivaBaseVisitor<Value> {

    @Override
    public Value visitValue(@NotNull ValueContext ctx) {
        String value = ctx.getText();
        Type type = TypeResolver.getFromValue(ctx);
        return new Value(type, value);
    }
}