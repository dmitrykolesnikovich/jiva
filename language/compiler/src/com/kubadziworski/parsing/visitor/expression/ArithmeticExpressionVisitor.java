package com.kubadziworski.parsing.visitor.expression;

import jiva.parser.JivaBaseVisitor;
import jiva.parser.JivaParser;
import jiva.parser.JivaParser.AddContext;
import jiva.parser.JivaParser.DivideContext;
import jiva.parser.JivaParser.ExpressionContext;
import jiva.parser.JivaParser.MultiplyContext;
import jiva.parser.JivaParser.SubstractContext;
import com.kubadziworski.domain.node.expression.Expression;
import com.kubadziworski.domain.node.expression.arthimetic.*;
import org.antlr.v4.runtime.misc.NotNull;

public class ArithmeticExpressionVisitor extends JivaBaseVisitor<ArthimeticExpression> {
    private final ExpressionVisitor expressionVisitor;

    public ArithmeticExpressionVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public ArthimeticExpression visitAdd(@NotNull AddContext ctx) {
        ExpressionContext leftExpression = ctx.expression(0);
        ExpressionContext rightExpression = ctx.expression(1);

        Expression leftExpress = leftExpression.accept(expressionVisitor);
        Expression rightExpress = rightExpression.accept(expressionVisitor);

        return new Addition(leftExpress, rightExpress);
    }

    @Override
    public ArthimeticExpression visitMultiply(@NotNull MultiplyContext ctx) {
        ExpressionContext leftExpression = ctx.expression(0);
        ExpressionContext rightExpression = ctx.expression(1);

        Expression leftExpress = leftExpression.accept(expressionVisitor);
        Expression rightExpress = rightExpression.accept(expressionVisitor);

        return new Multiplication(leftExpress, rightExpress);
    }

    @Override
    public ArthimeticExpression visitSubstract(@NotNull SubstractContext ctx) {
        ExpressionContext leftExpression = ctx.expression(0);
        ExpressionContext rightExpression = ctx.expression(1);

        Expression leftExpress = leftExpression.accept(expressionVisitor);
        Expression rightExpress = rightExpression.accept(expressionVisitor);

        return new Substraction(leftExpress, rightExpress);
    }

    @Override
    public ArthimeticExpression visitDivide(@NotNull DivideContext ctx) {
        ExpressionContext leftExpression = ctx.expression(0);
        ExpressionContext rightExpression = ctx.expression(1);

        Expression leftExpress = leftExpression.accept(expressionVisitor);
        Expression rightExpress = rightExpression.accept(expressionVisitor);

        return new Division(leftExpress, rightExpress);
    }
}