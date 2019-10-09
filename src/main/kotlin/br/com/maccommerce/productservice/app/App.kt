package br.com.maccommerce.productservice.app

import br.com.maccommerce.productservice.app.config.EnvironmentConfig
import br.com.maccommerce.productservice.app.config.appModules
import br.com.maccommerce.productservice.app.web.handler.ErrorHandler
import br.com.maccommerce.productservice.app.web.router.CategoryRouter
import br.com.maccommerce.productservice.app.web.router.ProductRouter
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.jetbrains.exposed.sql.Database
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

object App : KoinComponent {

    private fun setupKoin() {
        startKoin {
            printLogger()
            environmentProperties()
            modules(appModules)
        }
    }

    private fun setupDatabase() {
        runMigrations()
        connectToDatabase()
    }

    private fun runMigrations() {
        Flyway.configure().run {
            dataSource(
                EnvironmentConfig.jbdcDatabaseUrl,
                EnvironmentConfig.jbdcDatabaseUsername,
                EnvironmentConfig.jbdcDatabasePassword
            ).load()
        }.apply { migrate() }
    }

    private fun connectToDatabase() {
        HikariDataSource(HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = EnvironmentConfig.jbdcDatabaseUrl
            username = EnvironmentConfig.jbdcDatabaseUsername
            password = EnvironmentConfig.jbdcDatabasePassword
            minimumIdle = 0
        }).run { Database.connect(this) }
    }

    private val routes: List<RoutingHttpHandler> get() = (ProductRouter() + CategoryRouter())

    private fun startServer() {
        routes.toTypedArray().run {
            routes(*this).withFilter(ErrorHandler()).run {
                asServer(Jetty(port = EnvironmentConfig.applicationPort)).start()
            }
        }
    }

    fun start() = setupKoin().also { setupDatabase() }.also { startServer() }

}

fun main() = App.start()
