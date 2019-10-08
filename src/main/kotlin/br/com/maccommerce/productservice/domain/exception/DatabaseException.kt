package br.com.maccommerce.productservice.domain.exception

import br.com.maccommerce.productservice.domain.exception.ApiExceptionType.DATABASE_ERROR

class DatabaseException(override val message: String) : ApiException(DATABASE_ERROR, message)