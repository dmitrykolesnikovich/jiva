package jiva.util

import com.google.common.collect.BiMap
import com.google.common.collect.ImmutableBiMap
import jiva.domain.type.BultInType
import jiva.domain.type.ClassType
import jiva.domain.type.ClassType.Companion.Boolean
import jiva.domain.type.ClassType.Companion.Double
import jiva.domain.type.ClassType.Companion.Float
import jiva.domain.type.ClassType.Companion.Integer
import jiva.domain.type.Type

/**
 * Created by kuba on 17.05.16.
 */
object PrimitiveTypesWrapperFactory {

    private val types: BiMap<BultInType, ClassType> = ImmutableBiMap.of(
        BultInType.INT, Integer(),
        BultInType.BOOLEAN, Boolean(),
        BultInType.FLOAT, Float(),
        BultInType.DOUBLE, Double()
    )

    private val toPrimitiveMethodName: Map<Type, String> = mapOf(Integer() to "intValue")

    fun getPrimitiveForWrapper(type: Type): BultInType? {
        return types.inverse()[type]
    }

    fun getWrapperForPrimitive(type: Type?): ClassType? {
        return types[type]
    }

}
