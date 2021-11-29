package jiva.exception

import jiva.domain.node.expression.Argument
import jiva.domain.scope.Scope

/**
 * Created by kuba on 09.04.16.
 */
class MethodSignatureNotFoundException(scope: Scope, methodName: String, parameterTypes: List<Argument>) :
    RuntimeException("There is no method '$methodName' with parameters $parameterTypes")