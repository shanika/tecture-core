package io.tecture.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "software_system")
open class SoftwareSystemEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(nullable = false, unique = true)
    open var uuid: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    open var name: String = "",

    @Column(nullable = true)
    open var description: String? = null,
) : PanacheEntityBase
