package jiva.domain.node.statement

import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.node.expression.Expression
import jiva.domain.scope.Scope
import jiva.domain.type.Type
import jiva.exception.UnsupportedRangedLoopTypes
import jiva.util.TypeChecker

/**
 * Created by kuba on 23.04.16.
 */
class RangedForStatement(
    val iteratorVariableStatement: Statement,
    val startExpression: Expression,
    val endExpression: Expression,
    val statement: Statement,
    val iteratorVarName: String,
    val scope: Scope
) : Statement {

    val type: Type = startExpression.type

    init {
        val startExpressionType = startExpression.type
        val endExpressionType = endExpression.type
        val typesAreIntegers = TypeChecker.isInt(startExpressionType) || TypeChecker.isInt(endExpressionType)
        if (!typesAreIntegers) {
            throw UnsupportedRangedLoopTypes(startExpression, endExpression)
        }
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}
