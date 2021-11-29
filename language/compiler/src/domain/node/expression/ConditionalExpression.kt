package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.CompareSign
import jiva.domain.type.BultInType
import jiva.domain.type.Type
import jiva.exception.MixedComparisonNotAllowedException

/**
 * Created by kuba on 12.04.16.
 */
class ConditionalExpression(
    val leftExpression: Expression,
    val rightExpression: Expression,
    val compareSign: CompareSign
) :
    Expression {

    override val type: Type = BultInType.BOOLEAN
    val isPrimitiveComparison: Boolean

    init {
        val leftExpressionIsPrimitive = leftExpression.type.typeClass.isPrimitive
        val rightExpressionIsPrimitive = rightExpression.type.typeClass.isPrimitive
        isPrimitiveComparison = leftExpressionIsPrimitive && rightExpressionIsPrimitive
        val isObjectsComparison = !leftExpressionIsPrimitive && !rightExpressionIsPrimitive
        val isMixedComparison = !isPrimitiveComparison && !isObjectsComparison
        if (isMixedComparison) {
            throw MixedComparisonNotAllowedException(leftExpression.type, rightExpression.type)
        }
    }

    override fun accept(genrator: ExpressionGenerator) {
        genrator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }


}