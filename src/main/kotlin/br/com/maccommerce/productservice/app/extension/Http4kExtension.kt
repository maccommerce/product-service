package br.com.maccommerce.productservice.app.extension

import org.http4k.core.HttpHandler
import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Method.PUT
import org.http4k.routing.bind

infix fun String.get(action: HttpHandler) = this bind GET to action

infix fun String.post(action: HttpHandler) = this bind POST to action

infix fun String.put(action: HttpHandler) = this bind PUT to action

infix fun String.delete(action: HttpHandler) = this bind DELETE to action