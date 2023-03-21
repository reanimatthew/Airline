package com.example.airline.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@EnableWebSecurity
public class SecurityConfig {

    // TODO авторизацию можно настраивать на URL, а можно на определенные методы с помощью аннотации
    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, JWTAuthenticationFilter jwtAuthenticationFilter, JWTAuthorizationFilter jwtAuthorizationFilter, LogoutService logoutService) {
        //csfr - одна из защит атак на сервер
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/v1/login").permitAll()
                // .antMatchers("/api/v1/tickets/**").hasAuthority("PASSENGER")
                // если будут проблемы с Security - оставить просто .anyRequest().permitAll()
                    .anyRequest().permitAll()
                .and()
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                // если нам нужен какой-то еще функционал при выходе пользователя, напр. логирование выхода -
                // используем logoutService, в котором можно прописать этот ф-ционал
                // если же просто выходим - deleteCookies()
                .logout()
//                .addLogoutHandler(logoutService)
                    .deleteCookies("JWTToken")
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                    .logoutUrl("/api/v1/logout");

        return httpSecurity.build();
    }

    @Bean     //выполняет метод, результат метода будет бином
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
