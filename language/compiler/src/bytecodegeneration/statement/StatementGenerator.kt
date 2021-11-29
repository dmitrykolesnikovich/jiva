package jiva.bytecodegeneration.statement

import jiva.bytecodegeneration.expression.ExpressionGenerator
import jiva.domain.node.expression.*
import jiva.domain.node.expression.arthimetic.Addition
import jiva.domain.node.expression.arthimetic.Division
import jiva.domain.node.expression.arthimetic.Multiplication
import jiva.domain.node.expression.arthimetic.Substraction
import jiva.domain.node.statement.*
import jiva.domain.scope.Scope
import org.objectweb.asm.MethodVisitor

/**
 * Created by kuba on 29.03.16.
 */
class StatementGenerator(methodVisitor: MethodVisitor, scope: Scope) {

    private val expressionGenerator = ExpressionGenerator(methodVisitor, scope)
    private val printStatementGenerator = PrintStatementGenerator(expressionGenerator, methodVisitor)
    private val variableDeclarationStatementGenerator = VariableDeclarationStatementGenerator(this, expressionGenerator)
    private val forStatementGenerator = ForStatementGenerator(methodVisitor)
    private val blockStatementGenerator = BlockStatementGenerator(methodVisitor)
    private val ifStatementGenerator = IfStatementGenerator(this, expressionGenerator, methodVisitor)
    private val returnStatemenetGenerator = ReturnStatemenetGenerator(expressionGenerator, methodVisitor)
    private val assignmentStatementGenerator = AssignmentStatementGenerator(methodVisitor, expressionGenerator, scope)

    fun generate(printStatement: PrintStatement) {
        printStatementGenerator.generate(printStatement)
    }

    fun generate(variableDeclaration: VariableDeclaration) {
        variableDeclarationStatementGenerator.generate(variableDeclaration)
    }

    fun generate(functionCall: FunctionCall) {
        functionCall.accept(expressionGenerator)
    }

    fun generate(returnStatement: ReturnStatement) {
        returnStatemenetGenerator.generate(returnStatement)
    }

    fun generate(ifStatement: IfStatement) {
        ifStatementGenerator.generate(ifStatement)
    }

    fun generate(block: Block) {
        blockStatementGenerator.generate(block)
    }

    fun generate(rangedForStatement: RangedForStatement) {
        forStatementGenerator.generate(rangedForStatement)
    }

    fun generate(assignment: Assignment) {
        assignmentStatementGenerator.generate(assignment)
    }

    fun generate(superCall: SuperCall) {
        expressionGenerator.generate(superCall)
    }

    fun generate(constructorCall: ConstructorCall) {
        expressionGenerator.generate(constructorCall)
    }

    fun generate(addition: Addition) {
        expressionGenerator.generate(addition)
    }

    fun generate(parameter: Parameter) {
        expressionGenerator.generate(parameter)
    }

    fun generate(conditionalExpression: ConditionalExpression) {
        expressionGenerator.generate(conditionalExpression)
    }

    fun generate(multiplication: Multiplication?) {
        expressionGenerator.generate(multiplication)
    }

    fun generate(value: Value) {
        expressionGenerator.generate(value)
    }

    fun generate(substraction: Substraction) {
        expressionGenerator.generate(substraction)
    }

    fun generate(division: Division) {
        expressionGenerator.generate(division)
    }

    fun generate(emptyExpression: EmptyExpression) {
        expressionGenerator.generate(emptyExpression)
    }

    fun generate(localVariableReference: LocalVariableReference) {
        expressionGenerator.generate(localVariableReference)
    }

    fun generate(fieldReference: FieldReference) {
        expressionGenerator.generate(fieldReference)
    }

}