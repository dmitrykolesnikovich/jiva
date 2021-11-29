package jiva.exception

import jiva.domain.node.expression.arthimetic.ArthimeticExpression

/**
 * Created by kuba on 10.04.16.
 */
class UnsupportedArthimeticOperationException(expression: ArthimeticExpression) : RuntimeException(prepareMesage(expression)) {

    companion object {
        private fun prepareMesage(expression: ArthimeticExpression): String {
            val leftExpression = expression.leftExpression
            val rightExpression = expression.rightExpression
            return "Unsupported arthimetic operation between $leftExpression and $rightExpression"
        }
    }

}