package com.kubadziworski.parsing;

import jiva.parser.JivaLexer;
import jiva.parser.JivaParser;
import com.kubadziworski.domain.CompilationUnit;
import com.kubadziworski.parsing.visitor.CompilationUnitVisitor;
import org.antlr.v4.runtime.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * Created by kuba on 16.03.16.
 */
public class Parser {
    public CompilationUnit getCompilationUnit(String fileAbsolutePath) throws IOException {
        CharStream charStream = new ANTLRFileStream(fileAbsolutePath); //fileAbolutePath - file containing first enk code file
        JivaLexer lexer = new JivaLexer(charStream);  //create lexer (pass enk file to it)
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JivaParser parser = new JivaParser(tokenStream);

        ANTLRErrorListener errorListener = new JivaTreeWalkErrorListener(); // JivaTreeWalkErrorListener - handles parse tree visiting error events
        parser.addErrorListener(errorListener);

        CompilationUnitVisitor compilationUnitVisitor = new CompilationUnitVisitor();
        return parser.compilationUnit().accept(compilationUnitVisitor);
    }
}
