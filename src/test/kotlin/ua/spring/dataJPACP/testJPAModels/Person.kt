package ua.spring.dataJPACP.testJPAModels

import javax.persistence.*

@Entity
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    @Column
    var name: String? = null
}