package br.com.maccommerce.productservice.resources.extension

import br.com.maccommerce.productservice.domain.entity.Category
import br.com.maccommerce.productservice.resources.entity.CategoryTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toCategory() = Category(
    id = this[CategoryTable.id],
    name = this[CategoryTable.name],
    description = this[CategoryTable.description] ?: ""
)