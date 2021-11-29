package jiva.domain.type

import org.objectweb.asm.Opcodes

/**
 * Created by kuba on 30.04.16.
 */
enum class TypeSpecificOpcodes(
    val load: Int,
    val store: Int,
    val returnCode: Int,
    val add: Int,
    val substract: Int,
    val multiply: Int,
    val divide: Int
) {

    // values (-127,127) - one byte.
    INT(Opcodes.ILOAD, Opcodes.ISTORE, Opcodes.IRETURN, Opcodes.IADD, Opcodes.ISUB, Opcodes.IMUL, Opcodes.IDIV),
    LONG(Opcodes.LLOAD, Opcodes.LSTORE, Opcodes.LRETURN, Opcodes.LADD, Opcodes.LSUB, Opcodes.LMUL, Opcodes.LDIV), FLOAT(Opcodes.FLOAD, Opcodes.FSTORE, Opcodes.FRETURN, Opcodes.FADD, Opcodes.FSUB, Opcodes.FMUL, Opcodes.FDIV),
    DOUBLE(Opcodes.DLOAD, Opcodes.DSTORE, Opcodes.DRETURN, Opcodes.DADD, Opcodes.DSUB, Opcodes.DMUL, Opcodes.DDIV),
    VOID(Opcodes.ALOAD, Opcodes.ASTORE, Opcodes.RETURN, 0, 0, 0, 0),
    OBJECT(Opcodes.ALOAD, Opcodes.ASTORE, Opcodes.ARETURN, 0, 0, 0, 0);

}