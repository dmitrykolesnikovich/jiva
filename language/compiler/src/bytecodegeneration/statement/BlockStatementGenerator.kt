package jiva.bytecodegeneration.statement

import jiva.domain.node.statement.Block
import org.objectweb.asm.MethodVisitor

class BlockStatementGenerator(val methodVisitor: MethodVisitor) {

    fun generate(block: Block) {
        val newScope = block.scope
        val statements = block.statements
        val statementGenerator = StatementGenerator(methodVisitor, newScope)
        statements.stream().forEach { it.accept(statementGenerator) }
    }

}
