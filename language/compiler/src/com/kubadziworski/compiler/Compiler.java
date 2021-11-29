package com.kubadziworski.compiler;

import com.kubadziworski.bytecodegeneration.BytecodeGenerator;
import com.kubadziworski.domain.CompilationUnit;
import com.kubadziworski.parsing.Parser;
import com.kubadziworski.validation.ARGUMENT_ERRORS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

/**
 * Created by kuba on 15.03.16.
 */
public class Compiler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Compiler.class);

    public static void main(String[] args) throws Exception {
        try {
            new Compiler().compile(args);
        } catch (IOException exception) {
            LOGGER.error("ERROR: " + exception.getMessage());
        }
    }

    public void compile(String[] args) throws Exception {
        ARGUMENT_ERRORS argumentsErrors = getArgumentValidationErrors(args);
        if (argumentsErrors != ARGUMENT_ERRORS.NONE) {
            String errorMessage = argumentsErrors.getMessage();
            LOGGER.error(errorMessage);
            return;
        }
        File file = new File(args[0]);
        String fileAbsolutePath = file.getAbsolutePath();
        LOGGER.info("Trying to parse '{}'.", file.getAbsolutePath());
        CompilationUnit compilationUnit = new Parser().getCompilationUnit(fileAbsolutePath);
        LOGGER.info("Finished Parsing. Started compiling to bytecode.");
        saveBytecodeToClassFile(compilationUnit);
    }

    private ARGUMENT_ERRORS getArgumentValidationErrors(String[] args) {
        if (args.length != 1) {
            return ARGUMENT_ERRORS.NO_FILE;
        }
        String filePath = args[0];
        return ARGUMENT_ERRORS.NONE;
    }

    private void saveBytecodeToClassFile(CompilationUnit compilationUnit) throws IOException {
        BytecodeGenerator bytecodeGenerator = new BytecodeGenerator();
        byte[] bytecode = bytecodeGenerator.generate(compilationUnit);
        String className = compilationUnit.getClassName();
        String fileName = className + ".class";
        LOGGER.info("Finished Compiling. Saving bytecode to '{}'.", Paths.get(fileName).toAbsolutePath());
        OutputStream os = new FileOutputStream(fileName);
        os.write(bytecode);
        LOGGER.info("Done. To run compiled file execute: 'java {}' in current dir", className);
    }
}
