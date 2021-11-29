package jiva.parsing.visitor

import jiva.domain.Constructor
import jiva.domain.Function
import jiva.domain.node.statement.Statement
import jiva.domain.scope.FunctionSignature
import jiva.domain.scope.LocalVariable
import jiva.domain.scope.Scope
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.FunctionContext
import jiva.parsing.visitor.statement.StatementVisitor

class FunctionVisitor(scope: Scope) : JivaBaseVisitor<Function>() {

    private val scope: Scope = Scope(scope)

    override fun visitFunction(ctx: FunctionContext): Function {
        val signature = ctx.functionDeclaration().accept(FunctionSignatureVisitor(scope))!!
        scope.addLocalVariable(LocalVariable("this", scope.classType))
        addParametersAsLocalVariables(signature)
        val block = getBlock(ctx)
        return if (signature.name == scope.className) Constructor(signature, block) else Function(signature, block)
    }

    private fun addParametersAsLocalVariables(signature: FunctionSignature) {
        for (parameter in signature.parameters) {
            scope.addLocalVariable(LocalVariable(parameter.name, parameter.type))
        }
    }

    private fun getBlock(functionContext: FunctionContext): Statement {
        val statementVisitor = StatementVisitor(scope)
        val block = functionContext.block()
        return block.accept(statementVisitor)!!
    }

}
