package com.yukado.greenraffle.config;

import com.yukado.greenraffle.service.CustomSuccessHandler;
import com.yukado.greenraffle.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF deaktivieren (je nach Bedarf anpassen)
                .csrf(csrf -> csrf.disable())
                // Zugriffsregeln definieren:
                .authorizeHttpRequests(authz -> authz
                        // Statische Ressourcen und öffentliche Seiten sind für alle zugänglich
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/", "/about", "/index", "/login", "/raffles",
                                "/winnerstable", "/ticket", "/ticket/**", "/register", "/finished" )
                        .permitAll()
                        // Endpunkt, der nur für Benutzer mit Rolle USER zugänglich ist
                        .requestMatchers("/user/**").hasAuthority("USER")
                        // Endpunkte, die nur für den Admin zugänglich sind (zum Beispiel alle URLs unter /admin/)
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        // Alle weiteren Endpunkte verlangen mindestens eine Authentifizierung
                        .anyRequest().authenticated()
                )
                // Konfiguration des Form-Logins mit dem Custom Success Handler
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(customSuccessHandler)
                        .permitAll()
                )
                // Konfiguration des Logouts
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }


    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}