package com.example.airline.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.UUID;

@Component
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/api/v1/login");
    }

    @SneakyThrows
    @Override
    // в этом методе мы перекладываем логин и пароль из body jason'a в объект Authentication
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // ищет сеттеры в loginDTO, затем ищет в request поля из этих сеттеров и упаковывает их в объект класса LoginDTO
        LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword());
        return getAuthenticationManager().authenticate(authentication);
    }

    // далее Spring берет объект Authentication, проверяет его на разные условия, если все ок - передает его в метод successfulAuthentication(...)

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String login = authResult.getName();
        String token = "";
        String[] roles = authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);
        try {
//            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            Algorithm algorithm = Algorithm.HMAC256("testKey");
            token = JWT.create()
                    .withIssuer("airline")
                    .withSubject(login)
                    .withArrayClaim("roles", roles)
                    .withExpiresAt(ZonedDateTime.now().plusMinutes(30).toInstant())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }

        Cookie cookie = new Cookie("JWTToken", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1800);
        response.addCookie(cookie);
    }
}
