package com.lukasdiettrichwebsite.backend.userbackend




import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
class UserData (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:        Long,
    var firstName: String,
    var lastName:  String,
    var email:     String,
    var username:  String,
    var password:  String,
    var addressId: Long,
) {
}