package br.com.maccommerce.productservice.app.config

import br.com.maccommerce.productservice.domain.exception.EnvironmentVariableNotFoundException
import org.koin.core.KoinComponent

private const val JDBC_DATABASE_URL = "JDBC_DATABASE_URL"
private const val JDBC_DATABASE_USERNAME = "JDBC_DATABASE_USERNAME"
private const val JDBC_DATABASE_PASSWORD = "JDBC_DATABASE_PASSWORD"

object EnvironmentConfig : KoinComponent {

    val jbdcDatabaseUrl: String = getKoin().getProperty(JDBC_DATABASE_URL) ?:
    throw EnvironmentVariableNotFoundException(JDBC_DATABASE_URL)

    val jbdcDatabaseUsername: String = getKoin().getProperty(JDBC_DATABASE_USERNAME) ?:
    throw EnvironmentVariableNotFoundException(JDBC_DATABASE_USERNAME)

    val jbdcDatabasePassword: String = getKoin().getProperty(JDBC_DATABASE_PASSWORD) ?:
    throw EnvironmentVariableNotFoundException(JDBC_DATABASE_PASSWORD)

}
