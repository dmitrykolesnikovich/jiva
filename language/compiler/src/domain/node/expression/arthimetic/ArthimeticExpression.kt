package jiva.domain.node.expression.arthimetic

import jiva.domain.node.expression.Expression
import jiva.domain.type.BultInType
import jiva.domain.type.Type

/**
 * Created by kuba on 10.04.16.
 */
abstract class ArthimeticExpression(val leftExpression: Expression, val rightExpression: Expression) : Expression {

    override val type: Type = getCommonType(leftExpression, rightExpression)

    companion object {
        private fun getCommonType(leftExpression: Expression, rightExpression: Expression): Type {
            return if (rightExpression.type === BultInType.STRING) BultInType.STRING else leftExpression.type
        }
    }

}