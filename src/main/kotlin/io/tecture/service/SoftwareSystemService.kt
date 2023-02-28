package io.tecture.service

import io.tecture.dtos.CreateSystemRequestDto
import io.tecture.entity.SoftwareSystemEntity
import io.tecture.repository.SoftwareSystemRepository
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional

@ApplicationScoped
class SoftwareSystemService {

    @Inject
    private lateinit var softwareSystemRepository: SoftwareSystemRepository

    /**
     * Create a new software system
     */
    @Transactional
    fun create(softwareSystem: SoftwareSystemEntity): SoftwareSystemEntity {
        softwareSystemRepository.persist(softwareSystem)
        return softwareSystem
    }

    /**
     * Find a software system by UUID
     */
    fun findByUuid(uuid: UUID): SoftwareSystemEntity? {
        return softwareSystemRepository.findByUuid(uuid)
    }

    /**
     * Delete a software system by UUID
     */
    @Transactional
    fun delete(uuid: UUID) {
        softwareSystemRepository.delete("uuid", uuid)
    }

    /**
     * Find all software systems
     */
    fun findAll(): List<SoftwareSystemEntity> {
        return softwareSystemRepository.listAll()
    }

    fun create(softwareSystem: CreateSystemRequestDto): SoftwareSystemEntity {
        val softwareSystemEntity = SoftwareSystemEntity(
            UUID.randomUUID(),
            softwareSystem.name,
            null
        )
        return create(softwareSystemEntity)
    }
}
