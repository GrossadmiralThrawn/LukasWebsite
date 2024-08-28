package com.lukasdiettrichwebsite.backend.userbackend





import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain




@Configuration
@EnableWebSecurity
class SecurityConfig (private val userDetailsService: UserService){

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/user/statistics", "/user/dashboard").authenticated() // Sicherung der /statistics Route
                    .anyRequest().permitAll() // Alle anderen Routen sind frei zugänglich
            }
            .formLogin {
                it.loginPage("/user/login").permitAll()
            }
            .logout {
                it.logoutUrl("/user/logout").permitAll()
            }
        return http.build()
    }

    fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
    }
}