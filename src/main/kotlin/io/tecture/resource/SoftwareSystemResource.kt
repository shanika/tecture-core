package io.tecture.resource

import io.tecture.service.SoftwareSystemService
import io.tecture.modal.SoftwareSystem
import java.util.*
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/software-systems")
class SoftwareSystemResource @Inject constructor(private val softwareSystemService: SoftwareSystemService) {

    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    fun get(@PathParam("uuid") uuid: UUID): SoftwareSystem {
        val softwareSystemEntity = softwareSystemService.findByUuid(uuid)
            ?: throw NotFoundException("Software system not found with UUID: $uuid")
        return SoftwareSystem(softwareSystemEntity.uuid, softwareSystemEntity.name, softwareSystemEntity.description)
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
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

}
