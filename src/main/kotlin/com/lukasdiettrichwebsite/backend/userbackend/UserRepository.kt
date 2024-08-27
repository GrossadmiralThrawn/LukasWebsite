package com.lukasdiettrichwebsite.backend.userbackend




import org.springframework.data.jpa.repository.JpaRepository




interface UserRepository: JpaRepository<UserData, Long> {
    fun findByUsername(username: String): UserData?
    fun findByEmail(email: String): UserData?
}