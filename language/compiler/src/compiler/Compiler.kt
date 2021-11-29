package jiva.compiler

import jiva.bytecodegeneration.BytecodeGenerator
import jiva.domain.CompilationUnit
import jiva.parsing.Parser
import jiva.validation.ARGUMENT_ERRORS
import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.nio.file.Paths

/**
 * Created by kuba on 15.03.16.
 */
class Compiler {
    @Throws(Exception::class)
    fun compile(args: Array<String>) {
        val argumentsErrors = getArgumentValidationErrors(args)
        if (argumentsErrors != ARGUMENT_ERRORS.NONE) {
            val errorMessage = argumentsErrors.message
            LOGGER.error(errorMessage)
            return
        }
        val file = File(args[0])
        val fileAbsolutePath = file.absolutePath
        LOGGER.info("Trying to parse '{}'.", file.absolutePath)
        val compilationUnit = Parser().getCompilationUnit(fileAbsolutePath)
        LOGGER.info("Finished Parsing. Started compiling to bytecode.")
        saveBytecodeToClassFile(compilationUnit)
    }

    private fun getArgumentValidationErrors(args: Array<String>): ARGUMENT_ERRORS {
        if (args.size != 1) {
            return ARGUMENT_ERRORS.NO_FILE
        }
        val filePath = args[0]
        return ARGUMENT_ERRORS.NONE
    }

    @Throws(IOException::class)
    private fun saveBytecodeToClassFile(compilationUnit: CompilationUnit) {
        val bytecodeGenerator = BytecodeGenerator()
        val bytecode = bytecodeGenerator.generate(compilationUnit)
        val className = compilationUnit.className
        val fileName = "$className.class"
        LOGGER.info("Finished Compiling. Saving bytecode to '{}'.", Paths.get(fileName).toAbsolutePath())
        val os: OutputStream = FileOutputStream(fileName)
        os.write(bytecode)
        LOGGER.info("Done. To run compiled file execute: 'java {}' in current dir", className)
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(Compiler::class.java)

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                Compiler().compile(args)
            } catch (exception: IOException) {
                LOGGER.error("ERROR: " + exception.message)
            }
        }
    }
}