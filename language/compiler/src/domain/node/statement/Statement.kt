package jiva.domain.node.statement

import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.node.Node

/**
 * Created by kuba on 02.04.16.
 */
@FunctionalInterface
interface Statement : Node {
    fun accept(generator: StatementGenerator)
}