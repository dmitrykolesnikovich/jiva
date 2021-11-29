package jiva.parsing.visitor.expression

import jiva.domain.node.expression.FieldReference
import jiva.domain.node.expression.LocalVariableReference
import jiva.domain.node.expression.Reference
import jiva.domain.scope.Scope
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.VarReferenceContext

class VariableReferenceExpressionVisitor(private val scope: Scope) : JivaBaseVisitor<Reference>() {

    override fun visitVarReference(ctx: VarReferenceContext): Reference {
        val varName = ctx.text
        if (scope.isFieldExists(varName)) {
            val field = scope.getField(varName)
            return FieldReference(field)
        }
        val variable = scope.getLocalVariable(varName)
        return LocalVariableReference(variable)
    }

}
