package br.com.maccommerce.productservice.resources.repository

import br.com.maccommerce.productservice.domain.entity.Category
import br.com.maccommerce.productservice.domain.repository.CategoryRepository
import br.com.maccommerce.productservice.resources.entity.CategoryTable
import br.com.maccommerce.productservice.resources.extension.toCategory
import io.azam.ulidj.ULID
import org.jetbrains.exposed.sql.*

class CategoryRepositoryImpl : CategoryRepository {

    override fun persist(entity: Category) = transactionCatching {
        val ulid = ULID.random()

        CategoryTable.insert {
            it[id] = ulid
            it[name] = entity.name
            it[description] = entity.description
        }

        entity.copy(id = ulid)
    }

    override fun update(id: String, entity: Category) = transactionCatching {
        CategoryTable.update({ CategoryTable.id eq id }) {
            it[name] = entity.name
            if(entity.description.isNotEmpty()) {
                it[description] = entity.description
            }
        }

        entity
    }

    override fun delete(id: String) = transactionCatching {
        CategoryTable.deleteWhere { CategoryTable.id eq id }
    }.let { Unit }

    override fun findAll() = transactionCatching {
        CategoryTable.selectAll().map { it.toCategory() }
    }

    override fun findById(id: String) = transactionCatching {
        CategoryTable.select { CategoryTable.id eq id }.firstOrNull()?.toCategory()
    }

}
