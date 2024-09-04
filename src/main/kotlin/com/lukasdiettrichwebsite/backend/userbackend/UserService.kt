package com.lukasdiettrichwebsite.backend.userbackend

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder // Injektion des Encoders
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")

        return org.springframework.security.core.userdetails.User(
            user.username, user.password, emptyList()
        )
    }

    fun changePassword(userName: String, oldPassword: String, newPassword: String): Boolean {
        val user = userRepository.findByUsername(userName) ?: return false

        // Vergleiche das alte Passwort mit dem gespeicherten (verschlüsselten) Passwort
        if (passwordEncoder.matches(oldPassword, user.password)) {
            // Verschlüssele das neue Passwort vor dem Speichern
            user.password = passwordEncoder.encode(newPassword)
            userRepository.save(user)
            return true
        } else {
            return false
        }
    }
}
