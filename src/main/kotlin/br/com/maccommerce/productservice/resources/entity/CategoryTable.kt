package br.com.maccommerce.productservice.resources.entity

import br.com.maccommerce.productservice.resources.utils.VARCHAR_MAX
import io.azam.ulidj.ULID.ULID_LENGTH
import org.jetbrains.exposed.sql.Table

object CategoryTable : Table("category") {

    val id = varchar("id", ULID_LENGTH).primaryKey()
    val name = varchar("name", VARCHAR_MAX)
    val description = varchar("description", VARCHAR_MAX).nullable()

}
