package jiva.exception

import jiva.domain.type.Type

/**
 * Created by kuba on 20.05.16.
 */
class MixedComparisonNotAllowedException(leftType: Type, rightType: Type) :
    RuntimeException("Comparison between object and primitive is not supported  :$leftType  |  $rightType")