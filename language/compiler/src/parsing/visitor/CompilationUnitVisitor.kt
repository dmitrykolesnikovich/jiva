package jiva.parsing.visitor

import jiva.domain.CompilationUnit
import jiva.parser.JivaBaseVisitor
import jiva.parser.JivaParser.CompilationUnitContext
import org.antlr.v4.runtime.misc.NotNull

/**
 * Created by kuba on 28.03.16.
 */
class CompilationUnitVisitor : JivaBaseVisitor<CompilationUnit?>() {
    
    override fun visitCompilationUnit(ctx: CompilationUnitContext): CompilationUnit {
        val classVisitor = ClassVisitor()
        val classDeclarationContext = ctx.classDeclaration()
        val classDeclaration = classDeclarationContext.accept(classVisitor)!!
        return CompilationUnit(classDeclaration)
    }
    
}
