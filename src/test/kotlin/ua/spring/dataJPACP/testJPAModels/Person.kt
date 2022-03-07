package ua.spring.dataJPACP.testJPAModels

import jakarta.persistence.*

@Entity
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    @Column
    var name: String? = null
}