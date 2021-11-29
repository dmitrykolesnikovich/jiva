package jiva.parsing

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import org.slf4j.LoggerFactory

class JivaTreeWalkErrorListener : BaseErrorListener() {

    override fun syntaxError(recognizer: Recognizer<*, *>?, offendingSymbol: Any, line: Int, charPositionInLine: Int, msg: String, e: RecognitionException) {
        val errorFormat = "You fucked up at line %d,char %d :(. Details:%n%s"
        val errorMsg = String.format(errorFormat, line, charPositionInLine, msg)
        LOGGER.error(errorMsg)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(JivaTreeWalkErrorListener::class.java)
    }

}
