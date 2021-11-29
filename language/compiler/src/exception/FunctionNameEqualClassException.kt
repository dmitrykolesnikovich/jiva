package jiva.exception

/**
 * Created by kuba on 05.05.16.
 */
class FunctionNameEqualClassException(functionName: String) :
    RuntimeException("Function name cannot be same as the class : $functionName")