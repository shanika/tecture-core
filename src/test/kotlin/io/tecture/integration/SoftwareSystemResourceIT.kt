package io.tecture.integration

import io.quarkus.test.common.http.TestHTTPEndpoint
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.tecture.entity.SoftwareSystemEntity
import io.tecture.resource.SoftwareSystemResource
import io.tecture.service.SoftwareSystemService
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import javax.inject.Inject


/**
 * Integration test for the [io.tecture.resource.SoftwareSystemResource] REST resource.
 */
@QuarkusTest
@TestHTTPEndpoint(SoftwareSystemResource::class)
class SoftwareSystemResourceIT() {

    @Inject
    lateinit var softwareSystemService: SoftwareSystemService

    val uuid: UUID = UUID.randomUUID()

    @BeforeEach
    fun setup() {

        RestAssured.filters(RequestLoggingFilter(), ResponseLoggingFilter())

        softwareSystemService.create(
            SoftwareSystemEntity(
                uuid,
                "Software System 1",
                "Software System 1 Description"
            )
        )
    }

    /**
     * Test the [io.tecture.resource.SoftwareSystemResource.get] endpoint using Rest Assured.
     */
    @Test
    fun testFindSoftwareSystemByUuidEndpoint() {
        given()
            .`when`()["/$uuid"]
            .then()
            .statusCode(200)
            .body("uuid", equalTo(uuid.toString()))
            .body("name", equalTo("Software System 1"))
            .body("description", equalTo("Software System 1 Description"))
    }

     @AfterEach
     fun tearDown() {
         softwareSystemService.delete(uuid)
     }


}
