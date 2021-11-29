package com.kubadziworski.parsing.visitor;

import jiva.parser.JivaBaseVisitor;
import jiva.parser.JivaParser;
import com.kubadziworski.domain.scope.Field;
import com.kubadziworski.domain.scope.Scope;
import com.kubadziworski.domain.type.ClassType;
import com.kubadziworski.domain.type.Type;
import com.kubadziworski.util.TypeResolver;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by kuba on 13.05.16.
 */
public class FieldVisitor extends JivaBaseVisitor<Field> {

    private final Scope scope;

    public FieldVisitor(Scope scope) {
        this.scope = scope;
    }

    @Override
    public Field visitField(@NotNull JivaParser.FieldContext ctx) {
        Type owner = scope.getClassType();
        Type type = TypeResolver.getFromTypeContext(ctx.type());
        String name = ctx.name().getText();
        return new Field(name, owner, type);
    }
}
