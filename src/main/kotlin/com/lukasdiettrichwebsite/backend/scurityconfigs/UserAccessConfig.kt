package com.lukasdiettrichwebsite.backend.scurityconfigs





import com.lukasdiettrichwebsite.backend.userbackend.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain




@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailsService: UserService,
    private val passwordEncoderConfig: PasswordEncoderConfig
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/user/statistics", "/user/dashboard", "/user/changePassword")
                    .authenticated() // Sicherung der /statistics Route
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
            .passwordEncoder(passwordEncoderConfig.passwordEncoder())
    }
}