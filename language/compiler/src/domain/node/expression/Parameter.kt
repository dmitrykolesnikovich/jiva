package jiva.domain.node.expression

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.type.Type

/**
 * Created by kuba on 02.04.16.
 */
class Parameter(val name: String, override val type: Type, val defaultValue: Expression?) : Expression {
    override fun accept(genrator: ExpressionGenerator) {
        genrator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val parameter = other as Parameter
        return if (if (defaultValue != null) defaultValue != parameter.defaultValue else parameter.defaultValue != null) false else !if (type != null) type != parameter.type else parameter.type != null
    }

    override fun hashCode(): Int {
        var result = defaultValue?.hashCode() ?: 0
        result = 31 * result + (type.hashCode() ?: 0)
        return result
    }
}