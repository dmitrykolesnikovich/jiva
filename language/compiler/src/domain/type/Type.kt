package jiva.domain.type

/**
 * Created by kuba on 28.03.16.
 */
interface Type {
    val name: String
    val typeClass: Class<*>
    val descriptor: String
    val internalName: String
    val loadVariableOpcode: Int
    val storeVariableOpcode: Int
    val returnOpcode: Int
    val addOpcode: Int
    val substractOpcode: Int
    val multiplyOpcode: Int
    val dividOpcode: Int
}