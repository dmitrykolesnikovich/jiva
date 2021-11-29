package jiva.domain.node.statement

import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.node.Node

@FunctionalInterface
interface Statement : Node {
    fun accept(generator: StatementGenerator)
}