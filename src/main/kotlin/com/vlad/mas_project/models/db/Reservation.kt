package com.vlad.mas_project.models.db

import javax.persistence.*

@Entity
class Reservation(
    @Column(name = "date_from")
    var dateFrom: Long? = null,
    @Column(name = "date_to")
    var dateTo: Long? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    @ManyToOne
    var client: Client? = null
        set(value) {
            if (value == null) return
            value.addReservation(this)
            field = value
        }


    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "Reservation_and_Object",
        joinColumns = [JoinColumn(name = "res_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "obj_id", referencedColumnName = "id")]
    )
    var reservationObjects: MutableList<ReservationObject> = arrayListOf()
}