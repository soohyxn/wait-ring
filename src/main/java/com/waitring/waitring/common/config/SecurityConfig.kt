package com.waitring.waitring.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
                .cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .headers().frameOptions().disable()
                .and().build()
    }
}