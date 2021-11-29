package jiva.exception

import jiva.domain.node.expression.Expression

/**
 * Created by kuba on 12.04.16.
 */
class ComparisonBetweenDiferentTypesException(leftExpression: Expression, rightExpression: Expression) :
    RuntimeException("Comparison between types " + leftExpression.type + " and " + rightExpression.type + " not yet supported")