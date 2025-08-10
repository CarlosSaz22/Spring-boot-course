package com.api.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable()) // Desactivar CSRF
                .authorizeExchange(authz -> authz
                        .pathMatchers("/eureka/**").permitAll() // Rutas públicas
                        .anyExchange().authenticated()           // El resto autenticado
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt -> {
                            // Configuración adicional si es necesaria, o dejar vacio para valores por defecto
                        })
                );

        return http.build();
    }
    }