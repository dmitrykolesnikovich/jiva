package jiva.domain.type

/**
 * Created by kuba on 02.04.16.
 */
enum class BultInType(
    val id: String,
    override val typeClass: Class<*>,
    override val descriptor: String,
    private val opcodes: TypeSpecificOpcodes
) : Type {

    BOOLEAN("boolean", Boolean::class.javaPrimitiveType!!, "Z", TypeSpecificOpcodes.INT), INT(
        "int",
        Int::class.javaPrimitiveType!!,
        "I",
        TypeSpecificOpcodes.INT
    ),
    CHAR("char", Char::class.javaPrimitiveType!!, "C", TypeSpecificOpcodes.INT), BYTE(
        "byte",
        Byte::class.javaPrimitiveType!!,
        "B",
        TypeSpecificOpcodes.INT
    ),
    SHORT("short", Short::class.javaPrimitiveType!!, "S", TypeSpecificOpcodes.INT), LONG(
        "long",
        Long::class.javaPrimitiveType!!,
        "J",
        TypeSpecificOpcodes.LONG
    ),
    FLOAT("float", Float::class.javaPrimitiveType!!, "F", TypeSpecificOpcodes.FLOAT), DOUBLE(
        "double",
        Double::class.javaPrimitiveType!!,
        "D",
        TypeSpecificOpcodes.DOUBLE
    ),
    STRING("string", String::class.java, "Ljava/lang/String;", TypeSpecificOpcodes.OBJECT), BOOLEAN_ARR(
        "bool[]",
        BooleanArray::class.java,
        "[B",
        TypeSpecificOpcodes.OBJECT
    ),
    INT_ARR("int[]", IntArray::class.java, "[I", TypeSpecificOpcodes.OBJECT), CHAR_ARR(
        "char[]",
        CharArray::class.java,
        "[C",
        TypeSpecificOpcodes.OBJECT
    ),
    BYTE_ARR("byte[]", ByteArray::class.java, "[B", TypeSpecificOpcodes.OBJECT), SHORT_ARR(
        "short[]",
        ShortArray::class.java,
        "[S",
        TypeSpecificOpcodes.OBJECT
    ),
    LONG_ARR("long[]", LongArray::class.java, "[J", TypeSpecificOpcodes.OBJECT), FLOAT_ARR(
        "float[]",
        FloatArray::class.java,
        "[F",
        TypeSpecificOpcodes.OBJECT
    ),
    DOUBLE_ARR("double[]", DoubleArray::class.java, "[D", TypeSpecificOpcodes.OBJECT), STRING_ARR(
        "string[]",
        Array<String>::class.java,
        "[Ljava/lang/String;",
        TypeSpecificOpcodes.OBJECT
    ),
    NONE("", Void::class.javaPrimitiveType!!, "", TypeSpecificOpcodes.OBJECT), VOID(
        "void",
        Void.TYPE,
        "V",
        TypeSpecificOpcodes.VOID
    );

    override val internalName: String get() = descriptor
    override val loadVariableOpcode: Int get() = opcodes.load
    override val storeVariableOpcode: Int get() = opcodes.store
    override val returnOpcode: Int get() = opcodes.`return`
    override val addOpcode: Int get() = opcodes.add
    override val substractOpcode: Int get() = opcodes.substract
    override val multiplyOpcode: Int get() = opcodes.multiply
    override val dividOpcode: Int get() = opcodes.divide

}
