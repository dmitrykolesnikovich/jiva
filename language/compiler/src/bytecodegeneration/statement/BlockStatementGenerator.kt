package jiva.bytecodegeneration.statement

import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.node.statement.Block
import jiva.domain.node.statement.Statement
import org.objectweb.asm.MethodVisitor

class BlockStatementGenerator(private val methodVisitor: MethodVisitor) {
    fun generate(block: Block) {
        val newScope = block.scope
        val statements = block.statements
        val statementGenerator = StatementGenerator(methodVisitor, newScope)
        statements.stream().forEach { stmt: Statement -> stmt.accept(statementGenerator) }
    }
}