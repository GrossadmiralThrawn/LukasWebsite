package com.lukasdiettrichwebsite.backend.userbackend




import org.springframework.data.jpa.repository.JpaRepository




interface AddressRepository: JpaRepository<AddressData, ULong> {
    fun findByLocality(city: String): AddressData?
    fun findByCountry(country: String): AddressData?
    fun findByContinent(continent: Char): AddressData?
    fun findByLocalityAndCountry(country: String, city: String): AddressData?
    fun findByCountryAndContinent(country: String, continent: Char): AddressData?
    fun findByStreetNumberAndStreetAndSpecification(streetNumber: String, street: String, specification: String): AddressData?
}