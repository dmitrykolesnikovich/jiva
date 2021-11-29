package jiva.exception

import jiva.domain.node.expression.Argument
import jiva.domain.node.expression.Parameter

/**
 * Created by kuba on 10.05.16.
 */
class WrongArgumentNameException(argument: Argument, parameters: List<Parameter>) :
    RuntimeException("You are trying to call method with argument name" + argument.parameterName + " where parameters = " + parameters)