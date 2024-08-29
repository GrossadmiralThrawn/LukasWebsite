package com.lukasdiettrichwebsite.backend.userbackend

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


/**
 * @author Lukas Philipp Diettrich
 * @brief Please notice: the street number also contains address additions like letters (9a, 9b, ...)
 */
@Entity
data class AddressData(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id:            ULong = 0u,
    private val continent:     Char,
    private val country:       String,
    private val province:      String,
    private val postcode:      String,
    private val locality:      String,
    private val street:        String,
    private val specification: String,
    private val streetNumber:  String,
    private val suite:         String,
){
    fun getId(): ULong = id
}