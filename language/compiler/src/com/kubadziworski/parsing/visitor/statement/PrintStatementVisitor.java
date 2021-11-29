package com.kubadziworski.parsing.visitor.statement;

import jiva.parser.JivaBaseVisitor;
import jiva.parser.JivaParser;
import jiva.parser.JivaParser.ExpressionContext;
import jiva.parser.JivaParser.PrintStatementContext;
import com.kubadziworski.domain.node.expression.Expression;
import com.kubadziworski.domain.node.statement.PrintStatement;
import com.kubadziworski.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

public class PrintStatementVisitor extends JivaBaseVisitor<PrintStatement> {
    private final ExpressionVisitor expressionVisitor;

    public PrintStatementVisitor(ExpressionVisitor expressionVisitor) {
        this.expressionVisitor = expressionVisitor;
    }

    @Override
    public PrintStatement visitPrintStatement(@NotNull PrintStatementContext ctx) {
        ExpressionContext expressionCtx = ctx.expression();
        Expression expression = expressionCtx.accept(expressionVisitor);
        return new PrintStatement(expression);
    }
}