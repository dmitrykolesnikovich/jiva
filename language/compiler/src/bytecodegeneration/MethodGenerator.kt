package jiva.bytecodegeneration

import jiva.bytecodegeneration.statement.StatementGenerator
import jiva.domain.Constructor
import jiva.domain.Function
import jiva.domain.node.expression.SuperCall
import jiva.domain.node.expression.EmptyExpression
import jiva.domain.node.statement.Block
import jiva.domain.node.statement.ReturnStatement
import jiva.util.DescriptorFactory
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

/**
 * Created by kuba on 28.03.16.
 */
class MethodGenerator(private val classWriter: ClassWriter) {
    fun generate(function: Function) {
        val name = function.name
        val isMain = name == MAIN_FUN_NAME
        val description = DescriptorFactory.getMethodDescriptor(function)
        val block = function.rootStatement as Block
        val scope = block.scope
        val access = Opcodes.ACC_PUBLIC + if (isMain) Opcodes.ACC_STATIC else 0
        val mv = classWriter.visitMethod(access, name, description, null, null)
        mv.visitCode()
        val statementScopeGenrator = StatementGenerator(mv, scope)
        block.accept(statementScopeGenrator)
        appendReturnIfNotExists(function, block, statementScopeGenrator)
        mv.visitMaxs(-1, -1)
        mv.visitEnd()
    }

    fun generate(constructor: Constructor) {
        val block = constructor.rootStatement as Block
        val scope = block.scope
        val access = Opcodes.ACC_PUBLIC
        val description = DescriptorFactory.getMethodDescriptor(constructor)
        val mv = classWriter.visitMethod(access, "<init>", description, null, null)
        mv.visitCode()
        val statementScopeGenrator = StatementGenerator(mv, scope)
        SuperCall().accept(statementScopeGenrator)
        block.accept(statementScopeGenrator)
        appendReturnIfNotExists(constructor, block, statementScopeGenrator)
        mv.visitMaxs(-1, -1)
        mv.visitEnd()
    }

    private fun appendReturnIfNotExists(function: Function, block: Block, statementScopeGenrator: StatementGenerator) {
        var isLastStatementReturn = false
        if (!block.statements.isEmpty()) {
            val lastStatement = block.statements[block.statements.size - 1]
            isLastStatementReturn = lastStatement is ReturnStatement
        }
        if (!isLastStatementReturn) {
            val emptyExpression = EmptyExpression(function.returnType)
            val returnStatement = ReturnStatement(emptyExpression)
            returnStatement.accept(statementScopeGenrator)
        }
    }

    companion object {
        const val MAIN_FUN_NAME = "main"
    }
}