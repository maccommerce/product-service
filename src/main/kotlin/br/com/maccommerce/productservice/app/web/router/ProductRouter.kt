package br.com.maccommerce.productservice.app.web.router

import br.com.maccommerce.productservice.app.web.controller.CrudController
import br.com.maccommerce.productservice.app.web.controller.ProductController
import org.koin.core.KoinComponent
import org.koin.core.inject

object ProductRouter : KoinComponent, CrudRouter {

    override val controller: CrudController by inject<ProductController>()

    override fun invoke() = super.invoke().map { it.withBasePath("/products") }

}
