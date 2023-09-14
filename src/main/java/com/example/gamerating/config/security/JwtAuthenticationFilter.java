package com.example.gamerating.config.security;

import com.example.gamerating.domain.vo.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final TokenService tokenService;

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        UserVO user = new ObjectMapper().readValue(request.getInputStream(), UserVO.class);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(">>> Unable to retrieve the username or password");
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPass(), Collections.emptyList());
        usernamePasswordAuthenticationToken.setDetails(user);
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    @SneakyThrows
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        log.debug(">>> Authentication was successful for the user '{}', generation JWE token", authResult.getName());
        SignedJWT signedJWT = tokenService.createSignedJWT(authResult);
        String encryptedToken = tokenService.encryptToken(signedJWT);
        log.debug(">>> Token generated successfully, adding it to the response header");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "XSRF-TOKEN, " + jwtConfig.getHeader().getName());
        response.addHeader(jwtConfig.getHeader().getName(), jwtConfig.getHeader().getPrefix() + encryptedToken);
    }


}
