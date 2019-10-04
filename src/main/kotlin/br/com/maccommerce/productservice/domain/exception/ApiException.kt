package br.com.maccommerce.productservice.domain.exception

enum class ApiExceptionType {

    BAD_REQUEST,
    DATABASE_ERROR,
    INTERNAL_ERROR,
    NOT_FOUND

}

class ApiException(
    val type: ApiExceptionType,
    override val message: String
) : RuntimeException(message)
