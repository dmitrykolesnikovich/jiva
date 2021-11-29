package jiva.exception

import jiva.domain.scope.FunctionSignature
import jiva.parser.JivaParser

/**
 * Created by kuba on 06.04.16.
 */
class BadArgumentsSize(function: FunctionSignature?, calledParameters: List<JivaParser.ExpressionContext?>?) :
    RuntimeException("Bad arguments amount")