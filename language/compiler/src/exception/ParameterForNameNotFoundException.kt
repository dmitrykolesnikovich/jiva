package jiva.exception

import jiva.domain.node.expression.Parameter

/**
 * Created by kuba on 17.04.16.
 */
class ParameterForNameNotFoundException(name: String, parameters: List<Parameter>) : RuntimeException()