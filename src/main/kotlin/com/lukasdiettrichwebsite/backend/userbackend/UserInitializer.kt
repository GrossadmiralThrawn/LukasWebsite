package com.lukasdiettrichwebsite.backend.userbackend

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
class UserInitializer {

        @Bean
        fun init(userRepository: UserRepository, passwordEncoder: PasswordEncoder) = CommandLineRunner {
            val defaultUsername = "admin"
            val defaultPassword = "admin"

            // Prüfen, ob der Benutzer bereits existiert
            if (userRepository.findByUsername(defaultUsername) == null) {
                val user = UserData(
                    id = 0,
                    firstName = "Lukas Philipp",
                    lastName = "Diettrich",
                    email = "lukasd2000@gmx.de",
                    username = defaultUsername,
                    password = passwordEncoder.encode(defaultPassword),
                    addressId = 1234,
                )
                userRepository.save(user)
                println("Default user created with username: $defaultUsername and password: $defaultPassword")
            } else {
                println("Default user already exists")
            }
        }
    }