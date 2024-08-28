package com.lukasdiettrichwebsite.backend.userbackend

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
class UserInitializer {

    @Bean
    fun init(userRepository: UserRepository, addressRepository: AddressRepository, passwordEncoder: PasswordEncoder) = CommandLineRunner {
        val defaultUsername = "admin"
        val defaultPassword = "admin"

        // Prüfen, ob der Benutzer bereits existiert
        if (userRepository.findByUsername(defaultUsername) == null) {

            // Adresse erstellen und speichern
            val address = AddressData(
                continent = '0',
                country = "Germany",
                province = "Saxony-Anhalt",
                locality = "Petersberg OT Gutenberg",
                street = "Lange Straße",
                streetNumber = "9",
                suite = "2",
                postcode = "06193",
                specification = ""
            )
            val savedAddress = addressRepository.save(address)

            // Benutzer erstellen und speichern
            val user = UserData(
                id = 0,
                firstName = "Lukas Philipp",
                lastName = "Diettrich",
                email = "lukasd2000@gmx.de",
                username = defaultUsername,
                password = passwordEncoder.encode(defaultPassword),
                addressId = savedAddress.getId()
            )
            userRepository.save(user)

            println("Default user created with username: $defaultUsername and password: $defaultPassword")
        } else {
            println("Default user already exists")
        }
    }
    }