package com.kubadziworski.parsing.visitor.expression.function;

import jiva.parser.JivaBaseVisitor;
import jiva.parser.JivaParser.ArgumentListContext;
import jiva.parser.JivaParser.ConstructorCallContext;
import jiva.parser.JivaParser.FunctionCallContext;
import jiva.parser.JivaParser.SupercallContext;
import com.kubadziworski.domain.node.expression.*;
import com.kubadziworski.domain.scope.FunctionSignature;
import com.kubadziworski.domain.scope.LocalVariable;
import com.kubadziworski.domain.scope.Scope;
import com.kubadziworski.domain.type.ClassType;
import com.kubadziworski.exception.FunctionNameEqualClassException;
import com.kubadziworski.parsing.visitor.expression.ExpressionVisitor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CallExpressionVisitor extends JivaBaseVisitor<Call> {
    private final ExpressionVisitor expressionVisitor;
    private final Scope scope;

    public CallExpressionVisitor(ExpressionVisitor expressionVisitor, Scope scope) {
        this.expressionVisitor = expressionVisitor;
        this.scope = scope;
    }

    @Override
    public Call visitFunctionCall(@NotNull FunctionCallContext ctx) {
        String functionName = ctx.functionName().getText();
        if (functionName.equals(scope.getClassName())) {
            throw new FunctionNameEqualClassException(functionName);
        }
        List<Argument> arguments = getArgumentsForCall(ctx.argumentList());
        boolean ownerIsExplicit = ctx.owner != null;
        if (ownerIsExplicit) {
            Expression owner = ctx.owner.accept(expressionVisitor);
            FunctionSignature signature = scope.getMethodCallSignature(Optional.of(owner.getType()),functionName, arguments);
            return new FunctionCall(signature, arguments, owner);
        }
        ClassType thisType = new ClassType(scope.getClassName());
        FunctionSignature signature = scope.getMethodCallSignature(functionName, arguments);
        LocalVariable thisVariable = new LocalVariable("this",thisType);
        return new FunctionCall(signature, arguments, new LocalVariableReference(thisVariable));
    }

    @Override
    public Call visitConstructorCall(@NotNull ConstructorCallContext ctx) {
        String className = ctx.className().getText();
        List<Argument> arguments = getArgumentsForCall(ctx.argumentList());
        return new ConstructorCall(className, arguments);
    }

    @Override
    public Call visitSupercall(@NotNull SupercallContext ctx) {
        List<Argument> arguments = getArgumentsForCall(ctx.argumentList());
        return new SuperCall(arguments);
    }

    private List<Argument> getArgumentsForCall(ArgumentListContext argumentsListCtx) {
        if (argumentsListCtx != null) {
            ArgumentExpressionsListVisitor visitor = new ArgumentExpressionsListVisitor(expressionVisitor);
            return argumentsListCtx.accept(visitor);
        }
        return Collections.emptyList();
    }
}