package jiva.exception

import jiva.domain.scope.Scope

/**
 * Created by kuba on 13.05.16.
 */
class FieldNotFoundException(scope: Scope, fieldName: String) :
    RuntimeException("No field found for name $fieldName found in scope$scope")