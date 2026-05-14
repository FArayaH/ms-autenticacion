package com.hotel.ms_autenticacion.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //  Deshabilitamos CSRF porque no usaremos cookies/sesiones tradicionales
                .csrf(csrf -> csrf.disable())

                // EL PILAR STATELESS: Le decimos a Spring que no guarde sesiones en memoria
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //  Reglas de acceso a las URLs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/autenticacion/**").permitAll() // Dejamos que cualquiera intente hacer login
                        .anyRequest().authenticated() // Cualquier otra URL pedirá el token obligatoriamente
                );

        return http.build();
    }
}
