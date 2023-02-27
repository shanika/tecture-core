package io.tecture

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import org.testcontainers.containers.PostgreSQLContainer
import java.util.*


class PostgresResource : QuarkusTestResourceLifecycleManager {
    override fun start(): Map<String, String> {
        db.start()
        return Collections.singletonMap(
            "quarkus.datasource.url", db.jdbcUrl
        )
    }

    override fun stop() {
        db.stop()
    }

    companion object {
        var db: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:13")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("test")
    }
}
