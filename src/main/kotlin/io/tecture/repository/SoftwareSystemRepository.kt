package io.tecture.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import io.tecture.entity.SoftwareSystemEntity
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class SoftwareSystemRepository : PanacheRepositoryBase<SoftwareSystemEntity, Long> {

    fun findByUuid(uuid: UUID): SoftwareSystemEntity? {
        return find("uuid", uuid).firstResult()
    }
}
