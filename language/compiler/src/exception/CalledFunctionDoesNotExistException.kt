package jiva.exception

import jiva.domain.node.expression.FunctionCall
import jiva.domain.scope.Scope

/**
 * Created by kuba on 02.04.16.
 */
class CalledFunctionDoesNotExistException(private val functionCall: FunctionCall) : CompilationException() {

    constructor(functionCall: FunctionCall, e: ReflectiveOperationException?) : this(functionCall) {}
    constructor(functionCall: FunctionCall, scope: Scope?) : this(functionCall) {}

    override val message: String get() = "Function call" + functionCall.toString() + "does not exists"

}