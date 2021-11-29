package jiva.domain.type

import java.lang.ClassNotFoundException
import java.lang.RuntimeException
import com.google.common.collect.ImmutableMap
import jiva.domain.type.TypeSpecificOpcodes
import org.objectweb.asm.Opcodes
import java.util.*

/**
 * Created by kuba on 02.04.16.
 */
class ClassType(name: String) : Type {

    override val name: String = shortcuts[name] ?: name

    override val typeClass: Class<*>
        get() = try {
            Class.forName(name)
        } catch (e: ClassNotFoundException) {
            throw RuntimeException()
        }
    override val descriptor: String
        get() = "L$internalName;"
    override val internalName: String
        get() = name.replace(".", "/")
    override val loadVariableOpcode: Int
        get() = Opcodes.ALOAD
    override val storeVariableOpcode: Int
        get() = Opcodes.ASTORE
    override val returnOpcode: Int
        get() = Opcodes.ARETURN
    override val addOpcode: Int
        get() {
            throw RuntimeException("Addition operation not (yet ;) ) supported for custom objects")
        }
    override val substractOpcode: Int
        get() {
            throw RuntimeException("Substraction operation not (yet ;) ) supported for custom objects")
        }
    override val multiplyOpcode: Int
        get() {
            throw RuntimeException("Multiplcation operation not (yet ;) ) supported for custom objects")
        }
    override val dividOpcode: Int
        get() {
            throw RuntimeException("Division operation not (yet ;) ) supported for custom objects")
        }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val classType = o as ClassType
        return !(name != classType.name)
    }

    override fun hashCode(): Int {
        return name.hashCode() ?: 0
    }

    companion object {

        private val shortcuts: Map<String, String> = ImmutableMap.of("List", "java.util.ArrayList")

        fun Integer(): ClassType {
            return ClassType("java.lang.Integer")
        }

        fun Double(): ClassType {
            return ClassType("java.lang.Double")
        }

        fun Boolean(): ClassType {
            return ClassType("java.lang.Boolean")
        }

        fun Float(): ClassType {
            return ClassType("java.lang.Float")
        }

        fun String(): Type {
            return ClassType("java.lang.String")
        }
    }

}