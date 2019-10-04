package br.com.maccommerce.productservice.app.web.router

import br.com.maccommerce.productservice.app.extension.delete
import br.com.maccommerce.productservice.app.extension.post
import br.com.maccommerce.productservice.app.extension.put
import br.com.maccommerce.productservice.app.extension.get
import br.com.maccommerce.productservice.app.web.controller.ProductController
import org.koin.core.KoinComponent
import org.koin.core.inject

object ProductRouter : KoinComponent {

    private val controller by inject<ProductController>()

    operator fun invoke() = listOf(

        "/" post { controller.save(it) },

        "/{id}" put  { controller.update(it) },

        "/{id}" delete   { controller.delete(it) },

        "/" get { controller.findAll(it) },

        "/{id}" get { controller.findById(it) }

    ).map { it.withBasePath("/products") }

}
