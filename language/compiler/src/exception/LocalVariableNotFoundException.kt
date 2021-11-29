package jiva.exception

import jiva.domain.scope.Scope

/**
 * Created by kuba on 06.04.16.
 */
class LocalVariableNotFoundException(scope: Scope, variableName: String) :
    RuntimeException("No local varaible found for name $variableName found in scope$scope")