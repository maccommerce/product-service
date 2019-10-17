package br.com.maccommerce.productservice.app.config

import br.com.maccommerce.productservice.app.web.controller.CategoryController
import br.com.maccommerce.productservice.domain.repository.CategoryRepository
import br.com.maccommerce.productservice.domain.service.CategoryService
import br.com.maccommerce.productservice.domain.service.impl.CategoryServiceImpl
import br.com.maccommerce.productservice.resources.repository.CategoryRepositoryImpl
import org.koin.dsl.module

private val repositoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl() }
}

private val serviceModule = module {
    single<CategoryService> { CategoryServiceImpl(get()) }
}

private val controllerModule = module {
    single { CategoryController(get()) }
}

val appModules = repositoryModule + serviceModule + controllerModule
