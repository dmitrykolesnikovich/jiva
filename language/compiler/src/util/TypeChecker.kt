package jiva.util

import jiva.domain.type.BultInType
import jiva.domain.type.Type

/**
 * Created by kuba on 30.04.16.
 */
object TypeChecker {
    fun isInt(type: Type): Boolean = type === BultInType.INT
    fun isBool(type: Type): Boolean = type === BultInType.BOOLEAN
    fun isFloat(type: Type): Boolean = type === BultInType.FLOAT
    fun isDouble(type: Type): Boolean = type === BultInType.DOUBLE
}
