package br.com.maccommerce.productservice.app.web.handler

import br.com.maccommerce.productservice.app.web.entity.ApiExceptionResponse
import br.com.maccommerce.productservice.app.web.entity.toResponse
import br.com.maccommerce.productservice.domain.exception.ApiException
import br.com.maccommerce.productservice.domain.exception.ApiExceptionType.BAD_REQUEST
import br.com.maccommerce.productservice.domain.exception.ApiExceptionType.DATABASE_ERROR
import br.com.maccommerce.productservice.domain.exception.ApiExceptionType.INTERNAL_ERROR
import br.com.maccommerce.productservice.domain.exception.ApiExceptionType.NOT_FOUND
import org.http4k.core.Filter
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.format.Jackson

object ErrorHandler {

    operator fun invoke() = Filter { next ->
        { request ->
            try { next(request) } catch (exception: Throwable) {

                exception.printStackTrace(System.err).let {

                    when(exception) {

                        is ApiException -> when(exception.type) {

                            BAD_REQUEST -> Status.BAD_REQUEST to exception.toResponse()

                            DATABASE_ERROR -> Status.INTERNAL_SERVER_ERROR to exception.toResponse()

                            INTERNAL_ERROR -> Status.INTERNAL_SERVER_ERROR to exception.toResponse()

                            NOT_FOUND -> Status.NOT_FOUND to exception.toResponse()

                        }

                        else -> Status.INTERNAL_SERVER_ERROR to ApiExceptionResponse(INTERNAL_ERROR)

                    }.let { Response(it.first).body(Jackson.asJsonObject(it.second).toString()) }

                }
            }
        }
    }

}