package jiva.parsing

import jiva.domain.CompilationUnit
import jiva.parsing.visitor.CompilationUnitVisitor
import jiva.parser.JivaLexer
import jiva.parser.JivaParser
import org.antlr.v4.runtime.ANTLRErrorListener
import org.antlr.v4.runtime.ANTLRFileStream
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CommonTokenStream

/**
 * Created by kuba on 16.03.16.
 */
class Parser {

    fun getCompilationUnit(fileAbsolutePath: String?): CompilationUnit {
        val charStream: CharStream = ANTLRFileStream(fileAbsolutePath)
        val lexer = JivaLexer(charStream)
        val tokenStream = CommonTokenStream(lexer)
        val parser = JivaParser(tokenStream)
        val errorListener: ANTLRErrorListener = JivaTreeWalkErrorListener()
        parser.addErrorListener(errorListener)
        val compilationUnitVisitor = CompilationUnitVisitor()
        return parser.compilationUnit().accept(compilationUnitVisitor)!!
    }

}
