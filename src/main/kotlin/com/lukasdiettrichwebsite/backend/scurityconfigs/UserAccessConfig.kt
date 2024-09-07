package com.lukasdiettrichwebsite.backend.scurityconfigs




import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain




@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/user/statistics", "/user/dashboard", "/user/changePassword", "/user/addProject")
                    .authenticated()
                    .anyRequest().permitAll()
            }
            .formLogin {
                it.loginPage("/user/login").permitAll()
            }
            .logout {
                it.logoutUrl("/user/logout")
                    .logoutSuccessUrl("/user/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
            }
        return http.build()
    }





    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }
}
