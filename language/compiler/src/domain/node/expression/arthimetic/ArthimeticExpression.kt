package jiva.domain.node.expression.arthimetic

import jiva.domain.node.expression.Expression
import jiva.domain.type.BultInType
import jiva.domain.type.Type

/**
 * Created by kuba on 10.04.16.
 */
abstract class ArthimeticExpression protected constructor(leftExpression: Expression, rightExpression: Expression) : Expression {
    val leftExpression: Expression
    val rightExpression: Expression
    override val type: Type = getCommonType(leftExpression, rightExpression)

    companion object {
        private fun getCommonType(leftExpression: Expression, rightExpression: Expression): Type {
            return if (rightExpression.type === BultInType.STRING) BultInType.STRING else leftExpression.type
        }
    }

    init {
        this.leftExpression = leftExpression
        this.rightExpression = rightExpression
    }
}