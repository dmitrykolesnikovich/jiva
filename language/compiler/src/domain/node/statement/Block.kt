package jiva.domain.node.statement

import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.scope.Scope

/**
 * Created by kuba on 13.04.16.
 */
class Block(val scope: Scope, val statements: List<Statement>) : Statement {

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

    companion object {
        fun empty(scope: Scope): Block {
            return Block(scope, emptyList())
        }
    }

}