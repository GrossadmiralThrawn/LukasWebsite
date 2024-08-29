package com.lukasdiettrichwebsite.backend.userbackend




import org.springframework.data.jpa.repository.JpaRepository




interface UserRepository: JpaRepository<UserData, ULong> {
    fun findByUsername(username: String): UserData?
    fun findByEmail(email: String): UserData?
}