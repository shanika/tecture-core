package io.tecture.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import javax.persistence.*

@Entity
@Table(name = "element_type")
class ElementTypeEntity() : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    lateinit var name: String

    @ManyToMany
    @JoinTable(
        name = "element_type_in_connection_type",
        joinColumns = [JoinColumn(name = "element_type_id")],
        inverseJoinColumns = [JoinColumn(name = "connection_type_id")]
    )
    lateinit var inConnectionTypes: List<ConnectionTypeEntity>

    @ManyToMany
    @JoinTable(
        name = "element_type_out_connection_type",
        joinColumns = [JoinColumn(name = "element_type_id")],
        inverseJoinColumns = [JoinColumn(name = "connection_type_id")]
    )
    lateinit var outConnectionTypes: List<ConnectionTypeEntity>

    constructor(name: String) : this() {
        this.name = name
    }
}
