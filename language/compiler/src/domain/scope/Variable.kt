package jiva.domain.scope

import jiva.domain.type.Type

/**
 * Created by kuba on 13.05.16.
 */
interface Variable {
    val type: Type
    val name: String
}