package jiva.exception

import jiva.domain.scope.FunctionSignature

/**
 * Created by kuba on 08.05.16.
 */
class MethodWithNameAlreadyDefinedException(signature: FunctionSignature) :
    RuntimeException("Method already defined in scope :$signature")