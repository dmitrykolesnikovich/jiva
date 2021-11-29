package jiva.domain.node.statement

import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.node.expression.Expression
import java.util.*

/**
 * Created by kuba on 12.04.16.
 */
class IfStatement : Statement {

    val condition: Expression
    val trueStatement: Statement
    val falseStatement: Optional<Statement>

    constructor(condition: Expression, trueStatement: Statement, falseStatement: Statement?) {
        this.condition = condition
        this.trueStatement = trueStatement
        this.falseStatement = Optional.ofNullable(falseStatement)
    }

    constructor(condition: Expression, trueStatement: Statement) {
        this.condition = condition
        this.trueStatement = trueStatement
        falseStatement = Optional.empty()
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }
}