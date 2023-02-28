package io.tecture.resource

import io.tecture.dtos.CreateSystemRequestDto
import io.tecture.modal.SoftwareSystem
import io.tecture.service.SoftwareSystemService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import java.util.*
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/software-systems")
@Tag(name = "Software Systems")
class SoftwareSystemResource @Inject constructor(private val softwareSystemService: SoftwareSystemService) {

    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get a software system by UUID")
    fun get(@PathParam("uuid") uuid: UUID): SoftwareSystem {
        val softwareSystemEntity = softwareSystemService.findByUuid(uuid)
            ?: throw NotFoundException("Software system not found with UUID: $uuid")
        return SoftwareSystem(softwareSystemEntity.uuid, softwareSystemEntity.name, softwareSystemEntity.description)
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all software systems")
    fun getAll(): List<SoftwareSystem> {
        val softwareSystemEntities = softwareSystemService.findAll()
        return softwareSystemEntities.map { softwareSystemEntity ->
            SoftwareSystem(
                softwareSystemEntity.uuid,
                softwareSystemEntity.name,
                softwareSystemEntity.description
            )
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new software system")
    fun create(createSystemRequestDto: CreateSystemRequestDto): SoftwareSystem {
        val softwareSystemEntity = softwareSystemService.create(createSystemRequestDto)
        return SoftwareSystem(softwareSystemEntity.uuid, softwareSystemEntity.name, softwareSystemEntity.description)
    }

}
