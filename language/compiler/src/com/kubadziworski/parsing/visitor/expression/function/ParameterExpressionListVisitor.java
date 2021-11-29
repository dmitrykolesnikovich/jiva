package com.kubadziworski.parsing.visitor.expression.function;

import com.google.common.collect.Lists;
import jiva.parser.JivaBaseVisitor;
import jiva.parser.JivaParser;
import jiva.parser.JivaParser.ParameterContext;
import jiva.parser.JivaParser.ParameterWithDefaultValueContext;
import jiva.parser.JivaParser.ParametersListContext;
import com.kubadziworski.domain.node.expression.Parameter;
import com.kubadziworski.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuba on 09.05.16.
 */
public class ParameterExpressionListVisitor extends JivaBaseVisitor<List<Parameter>> {

    private final ExpressionVisitor expressionVisitor;

    public ParameterExpressionListVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public List<Parameter> visitParametersList(@NotNull ParametersListContext ctx) {
        List<ParameterContext> paramsCtx = ctx.parameter();
        ParameterExpressionVisitor parameterExpressionVisitor = new ParameterExpressionVisitor(expressionVisitor);
        List<Parameter> parameters = new ArrayList<>();
        if(paramsCtx != null) {
            List<Parameter> params = Lists.transform(paramsCtx, p -> p.accept(parameterExpressionVisitor));
            parameters.addAll(params);
        }
        List<ParameterWithDefaultValueContext> paramsWithDefaultValueCtx = ctx.parameterWithDefaultValue();
        if(paramsWithDefaultValueCtx != null) {
            List<Parameter> params = Lists.transform(paramsWithDefaultValueCtx, p -> p.accept(parameterExpressionVisitor));
            parameters.addAll(params);
        }
        return parameters;
    }

}

