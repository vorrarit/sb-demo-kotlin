package com.example.demo

import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configurable
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.authorizeRequests()
//                .antMatchers("/h2-console/**").anonymous()
                .antMatchers("/users/**").fullyAuthenticated()
                .and().httpBasic()

        // required for h2-console
        http.csrf().disable()
        http.headers().frameOptions().disable()

    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
        val user = User.withDefaultPasswordEncoder()
                .username("bfadmin")
                .password("password")
                .roles("USER")
                .build()
        return InMemoryUserDetailsManager(user)
    }

}