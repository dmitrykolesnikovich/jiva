package jiva.domain.node.expression

/**
 * Created by kuba on 05.05.16.
 */
interface Call : Expression {
    val arguments: List<Argument>
    val identifier: String
}