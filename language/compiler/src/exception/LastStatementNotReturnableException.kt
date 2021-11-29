package jiva.exception

import jiva.domain.node.statement.Statement

/**
 * Created by kuba on 11.04.16.
 */
class LastStatementNotReturnableException(lastStatement: Statement) :
    RuntimeException("The statement $lastStatement is a last statement in a functon, but it is not an expression!")