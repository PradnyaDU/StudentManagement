package com.FirstProject.StudentManagement.configclasses;

import com.FirstProject.StudentManagement.filter.JWTFilter;
import com.FirstProject.StudentManagement.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    private final CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JWTFilter jwtFilter;

    public SpringSecurity(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http.csrf(csrf -> csrf.disable()).authenticationProvider(authenticationProvider()).authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/login").permitAll()

                        .requestMatchers("/students/**").permitAll()
                        // .hasAnyAuthority("CREATE_STUDENT", "READ_STUDENT")
                        //.requestMatchers(HttpMethod.POST, "/students/sendMail").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS))

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//                .httpBasic(basic -> basic
//                        .authenticationEntryPoint((request, response, authException) -> {
//
//                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                            response.setContentType("application/json");
//
//                            response.getWriter().write(
//                                    "{\"errorMessage\":\"" + authException.getMessage() + "\"}"
//                            );
//                        })
//                );

        return http.build();
    }
}