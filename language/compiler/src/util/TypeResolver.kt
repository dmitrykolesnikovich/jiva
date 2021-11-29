package jiva.util

import jiva.domain.type.BultInType
import jiva.domain.type.ClassType
import jiva.domain.type.Type
import jiva.parser.JivaParser
import jiva.parser.JivaParser.ValueContext
import org.apache.commons.lang3.StringUtils

/**
 * Created by kuba on 02.04.16.
 */
object TypeResolver {

    fun getFromTypeContext(typeContext: JivaParser.TypeContext?): Type {
        return if (typeContext == null) BultInType.VOID else getFromTypeName(typeContext.text)
    }

    fun getFromTypeName(typeName: String): Type {
        if (typeName == "java.lang.String") return BultInType.STRING
        val builtInType: Type? = getBuiltInType(typeName)
        return builtInType ?: ClassType(typeName)
    }

    fun getFromValue(value: ValueContext): Type {
        val stringValue = value.text
        if (StringUtils.isEmpty(stringValue)) return BultInType.VOID
        if (value.NUMBER() != null) {
            when {
                stringValue.toIntOrNull() != null -> return BultInType.INT
                stringValue.toFloatOrNull() != null -> return BultInType.FLOAT
                stringValue.toDoubleOrNull() != null -> return BultInType.DOUBLE
            }
        } else if (value.BOOL() != null) {
            return BultInType.BOOLEAN
        }
        return BultInType.STRING
    }

    fun getValueFromString(stringValue: String, type: Type): Any? {
        var stringValue = stringValue
        if (TypeChecker.isInt(type)) {
            return Integer.valueOf(stringValue)
        }
        if (TypeChecker.isFloat(type)) {
            return java.lang.Float.valueOf(stringValue)
        }
        if (TypeChecker.isDouble(type)) {
            return java.lang.Double.valueOf(stringValue)
        }
        if (TypeChecker.isBool(type)) {
            return stringValue.toBoolean()
        }
        if (type === BultInType.STRING) {
            stringValue = StringUtils.removeStart(stringValue, "\"")
            stringValue = StringUtils.removeEnd(stringValue, "\"")
            return stringValue
        }
        throw AssertionError("Objects not yet implemented!")
    }

    private fun getBuiltInType(typeName: String): BultInType? {
        return BultInType.values().firstOrNull { it.id == typeName }
    }

}