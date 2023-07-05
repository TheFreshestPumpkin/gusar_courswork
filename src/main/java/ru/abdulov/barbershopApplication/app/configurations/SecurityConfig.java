package ru.abdulov.barbershopApplication.app.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/** Данный класс управляет настройками безопасности запросов */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/",
                                "/barber",
                                "/barber/*",
                                "/images/*",
                                "/user/*",
                                "/registration"
                                )
                        .permitAll()
                        .requestMatchers("/manager","/manager/*")
                        .hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers("/admin","admin/*")
                        .hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                        .formLogin((form) -> form
                                .loginPage("/login")
                                .permitAll()
                        )
                        .logout((logout) -> logout.permitAll());
        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
