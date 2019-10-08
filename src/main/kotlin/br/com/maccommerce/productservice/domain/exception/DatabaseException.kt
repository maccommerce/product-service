package br.com.maccommerce.productservice.domain.exception

import br.com.maccommerce.productservice.domain.exception.ApiExceptionType.DATABASE_ERROR

class DatabaseException : ApiException(DATABASE_ERROR, "Could not perform the database operation.")