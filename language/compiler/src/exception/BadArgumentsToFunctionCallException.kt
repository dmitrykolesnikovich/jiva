package jiva.exception

import jiva.domain.node.expression.Call

/**
 * Created by kuba on 16.04.16.
 */
class BadArgumentsToFunctionCallException(functionCall: Call) :
    RuntimeException("You called function with bad arguments $functionCall")