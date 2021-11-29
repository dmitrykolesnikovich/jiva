package jiva.parsing.visitor

import jiva.domain.ClassDeclaration
import jiva.domain.Constructor
import jiva.domain.Function
import jiva.domain.MetaData
import jiva.domain.node.expression.ConstructorCall
import jiva.domain.node.expression.FunctionCall
import jiva.domain.node.expression.Parameter
import jiva.domain.node.statement.Block
import jiva.domain.scope.FunctionSignature
import jiva.domain.scope.Scope
import jiva.domain.type.BultInType
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.*
import java.util.stream.Collectors

/**
 * Created by kuba on 01.04.16.
 */
class ClassVisitor : JivaBaseVisitor<ClassDeclaration>() {

    lateinit var scope: Scope

    override fun visitClassDeclaration(ctx: ClassDeclarationContext): ClassDeclaration {
        val metaData = MetaData(ctx.className().text, "java.lang.Object")
        scope = Scope(metaData)
        val name = ctx.className().text
        val fieldVisitor = FieldVisitor(scope)
        val functionSignatureVisitor = FunctionSignatureVisitor(scope)
        val methodsCtx = ctx.classBody().function()
        val fields = ctx.classBody().field().stream().map { field: FieldContext -> field.accept(fieldVisitor)!! }
            .peek { scope.addField(it) }
            .collect(Collectors.toList())
        methodsCtx.stream()
            .map { method: FunctionContext -> method.functionDeclaration().accept(functionSignatureVisitor)!! }
            .forEach { scope.addSignature(it) }
        val defaultConstructorExists = scope.isParameterLessSignatureExists(name)
        addDefaultConstructorSignatureToScope(name, defaultConstructorExists)
        val methods = methodsCtx.stream().map { it.accept(FunctionVisitor(scope))!! }.collect(Collectors.toList())
        if (!defaultConstructorExists) {
            methods.add(defaultConstructor)
        }
        val startMethodDefined = scope.isParameterLessSignatureExists("start")
        if (startMethodDefined) {
            methods.add(generatedMainMethod)
        }
        return ClassDeclaration(name, fields, methods)
    }

    /*internals*/

    private fun addDefaultConstructorSignatureToScope(name: String, defaultConstructorExists: Boolean) {
        if (!defaultConstructorExists) {
            val constructorSignature = FunctionSignature(name, emptyList(), BultInType.VOID)
            scope.addSignature(constructorSignature)
        }
    }

    private val defaultConstructor: Constructor
        get() = Constructor(scope.getMethodCallSignatureWithoutParameters(scope.className), Block.empty(scope))

    private val generatedMainMethod: Function
        get() {
            val args = Parameter("args", BultInType.STRING_ARR, null)
            val functionSignature = FunctionSignature("main", listOf(args), BultInType.VOID)
            val constructorCall = ConstructorCall(scope.className)
            val startFunSignature = FunctionSignature("start", emptyList(), BultInType.VOID)
            val startFunctionCall = FunctionCall(startFunSignature, emptyList(), scope.classType)
            val block = Block(Scope(scope), listOf(constructorCall, startFunctionCall))
            return Function(functionSignature, block)
        }

}