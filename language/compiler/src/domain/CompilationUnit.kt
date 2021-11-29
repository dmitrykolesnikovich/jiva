package jiva.domain

/**
 * Created by kuba on 28.03.16.
 */
class CompilationUnit(val classDeclaration: ClassDeclaration) {
    val className: String = classDeclaration.name
}