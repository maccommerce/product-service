package br.com.maccommerce.productservice.app.web.router

import br.com.maccommerce.productservice.app.web.controller.CategoryController
import br.com.maccommerce.productservice.app.web.controller.CrudController
import org.koin.core.KoinComponent
import org.koin.core.inject

object CategoryRouter : KoinComponent, CrudRouter {

    override val controller: CrudController by inject<CategoryController>()

    override fun invoke() = super.invoke().map { it.withBasePath("/categories") }

}