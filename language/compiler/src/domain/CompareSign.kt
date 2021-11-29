package jiva.domain

import org.objectweb.asm.Opcodes

/**
 * Created by kuba on 12.04.16.
 */
enum class CompareSign(private val sign: String, val opcode: Int) {

    EQUAL("==", Opcodes.IFEQ),
    NOT_EQUAL("!=", Opcodes.IFNE),
    LESS("<", Opcodes.IFLT),
    GREATER(">", Opcodes.IFGT),
    LESS_OR_EQUAL("<=", Opcodes.IFLE),
    GRATER_OR_EQAL(">=", Opcodes.IFGE);

    companion object {
        fun fromString(sign: String): CompareSign {
            return values().firstOrNull { cmpSign: CompareSign -> cmpSign.sign == sign }
                ?: throw RuntimeException("Sign not implemented")
        }
    }

}
