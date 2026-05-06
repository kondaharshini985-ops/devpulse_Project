package com.example.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailService userdetails;
    private final jwtUtil jwtutil;

    public SecurityConfig(CustomUserDetailService userdetails, jwtUtil jwtutil) {
        this.userdetails = userdetails;
        this.jwtutil = jwtutil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> {}) // ✅ FIXED (new syntax)
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            		 .requestMatchers(
            	                "/swagger-ui/**",
            	                "/v3/api-docs/**",
            	                "/swagger-ui/**"
            	            ).permitAll()

                // ✅ PUBLIC APIs
                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/ideas").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/ideas/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/implementation/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/implementations").permitAll()

                // ✅ ADMIN ONLY
                .requestMatchers(HttpMethod.GET, "/api/users").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/users/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAuthority("ROLE_ADMIN")

                // ✅ USER + ADMIN
                .requestMatchers(HttpMethod.POST, "/api/ideas").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/ideas/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/ideas/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/implementations").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/implementations/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/implementations/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/votes").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                // ✅ ALL OTHER REQUESTS NEED LOGIN
                .anyRequest().authenticated()
            )

            // 🔥 JWT FILTER
            .addFilterBefore(new JWTFilter(userdetails, jwtutil),
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ CORS CONFIG (VERY IMPORTANT)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}