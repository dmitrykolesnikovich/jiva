package jiva.domain.node.expression.arthimetic

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.node.expression.Expression

/**
 * Created by kuba on 10.04.16.
 */
class Substraction(leftExpress: Expression, rightExpress: Expression) : ArthimeticExpression(leftExpress, rightExpress) {

    override fun accept(generator: ExpressionGenerator) {
        generator.generate(this)
    }

    override fun accept(generator: StatementGenerator) {
        generator.generate(this)
    }

}