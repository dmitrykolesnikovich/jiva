package jiva.parsing.visitor

import jiva.domain.scope.Field
import jiva.domain.scope.Scope
import jiva.util.TypeResolver
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.FieldContext
import org.antlr.v4.runtime.misc.NotNull

/**
 * Created by kuba on 13.05.16.
 */
class FieldVisitor(private val scope: Scope) : JivaBaseVisitor<Field?>() {
    override fun visitField(@NotNull ctx: FieldContext): Field {
        val owner = scope.classType
        val type = TypeResolver.getFromTypeContext(ctx.type())
        val name = ctx.name().text
        return Field(name, owner, type)
    }
}