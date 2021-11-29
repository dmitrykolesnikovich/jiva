package jiva.exception

import jiva.domain.node.expression.Expression

/**
 * Created by kuba on 23.04.16.
 */
class UnsupportedRangedLoopTypes(startExpression: Expression, endExpression: Expression) :
    RuntimeException("Only integer types are supported so far")