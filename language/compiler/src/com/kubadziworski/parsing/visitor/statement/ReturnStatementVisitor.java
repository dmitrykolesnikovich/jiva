package com.kubadziworski.parsing.visitor.statement;

import jiva.parser.JivaBaseVisitor;
import jiva.parser.JivaParser;
import jiva.parser.JivaParser.ReturnVoidContext;
import jiva.parser.JivaParser.ReturnWithValueContext;
import com.kubadziworski.domain.node.expression.EmptyExpression;
import com.kubadziworski.domain.node.expression.Expression;
import com.kubadziworski.domain.node.statement.ReturnStatement;
import com.kubadziworski.domain.type.BultInType;
import com.kubadziworski.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class ReturnStatementVisitor extends JivaBaseVisitor<ReturnStatement> {
    private final ExpressionVisitor expressionVisitor;

    public ReturnStatementVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public ReturnStatement visitReturnVoid(@NotNull ReturnVoidContext ctx) {
        return new ReturnStatement(new EmptyExpression(BultInType.VOID));
    }

    @Override
    public ReturnStatement visitReturnWithValue(@NotNull ReturnWithValueContext ctx) {
        Expression expression = ctx.expression().accept(expressionVisitor);
        return new ReturnStatement(expression);
    }
}