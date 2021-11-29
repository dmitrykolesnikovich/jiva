package jiva.parsing.visitor.statement

import jiva.domain.node.statement.Block
import jiva.domain.scope.Scope
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.BlockContext

class BlockStatementVisitor(private val scope: Scope) : JivaBaseVisitor<Block?>() {

    override fun visitBlock(ctx: BlockContext): Block {
        val blockstatementsCtx = ctx.statement()
        val newScope = Scope(scope)
        val statementVisitor = StatementVisitor(newScope)
        val statements = blockstatementsCtx.map { it.accept(statementVisitor)!! }
        return Block(newScope, statements)
    }

}
