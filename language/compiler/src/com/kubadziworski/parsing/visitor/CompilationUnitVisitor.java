package com.kubadziworski.parsing.visitor;

import jiva.parser.JivaBaseVisitor;
import jiva.parser.JivaParser;
import jiva.parser.JivaParser.ClassDeclarationContext;
import jiva.parser.JivaParser.CompilationUnitContext;
import com.kubadziworski.domain.CompilationUnit;
import com.kubadziworski.domain.ClassDeclaration;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Created by kuba on 28.03.16.
 */
public class CompilationUnitVisitor extends JivaBaseVisitor<CompilationUnit> {

    @Override
    public CompilationUnit visitCompilationUnit(@NotNull CompilationUnitContext ctx) {
        ClassVisitor classVisitor = new ClassVisitor();
        ClassDeclarationContext classDeclarationContext = ctx.classDeclaration();
        ClassDeclaration classDeclaration = classDeclarationContext.accept(classVisitor);
        return new CompilationUnit(classDeclaration);
    }
}
