package jiva.exception

/**
 * Created by kuba on 04.05.16.
 */
class ClassNotFoundForNameException(className: String) : RuntimeException("class not found $className")