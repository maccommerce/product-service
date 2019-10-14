import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
        classpath("org.flywaydb:flyway-gradle-plugin:6.0.4")
    }
}

plugins {
    application
    kotlin("jvm") version "1.3.50"
    id("org.flywaydb.flyway") version "6.0.4"
}

group = "br.com.maccommerce"
version = "1.0.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

application {
    mainClassName = "br.com.maccommerce.productservice.app.AppKt"
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }

    from(configurations.runtimeClasspath.map { configuration ->
        configuration.asFileTree.map {
            if(it.isDirectory) it else zipTree(it)
        }
    })

    archiveFileName.set(project.name)

    // removing signed files from jar
    exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
}

tasks {
    withType<Test> {
        useJUnitPlatform {
            includeEngines("spek2")
        }
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

sourceSets {
    main {
        java {
            srcDirs("src/main/commons")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.http4k:http4k-core:3.187.0")
    implementation("org.http4k:http4k-server-jetty:3.187.0")
    implementation("org.http4k:http4k-format-jackson:3.187.0")
    implementation("org.jetbrains.exposed:exposed:0.17.4")
    implementation("org.koin:koin-core:2.0.1")
    implementation("com.zaxxer:HikariCP:3.4.1")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.postgresql:postgresql:42.2.8")
    implementation("org.flywaydb:flyway-core:6.0.4")
    implementation("io.azam.ulidj:ulidj:1.0.0")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("com.opentable.components:otj-pg-embedded:0.13.2")
    testImplementation("org.junit.platform:junit-platform-engine:1.5.2")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.7") {
        exclude(group = "org.jetbrains.kotlin")
    }
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.7") {
        exclude(group = "org.junit.platform")
        exclude(group = "org.jetbrains.kotlin")
    }
}
