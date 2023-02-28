package io.tecture.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "software_system")
open class SoftwareSystemEntity() : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    lateinit var uuid: UUID

    @Column(nullable = false)
    lateinit var name: String

    @Column(nullable = true)
    var description: String? = null
    constructor(uuid: UUID, name: String, description: String?) : this() {
        this.uuid = uuid
        this.name = name
        this.description = description
    }
}
