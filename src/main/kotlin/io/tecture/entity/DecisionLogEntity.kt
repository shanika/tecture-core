package io.tecture.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "decision_log")
open class DecisionLogEntity() : PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "software_system_id")
    lateinit var softwareSystem: SoftwareSystemEntity

    @Column(nullable = false)
    var timestamp: Long = Date().time
}
