package com.example.gamerating.config.security;

import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final TokenConverter tokenConverter;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(jwtConfig.getHeader().getName());

        if (!StringUtils.startsWith(header, jwtConfig.getHeader().getPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(jwtConfig.getHeader().getPrefix(), StringUtils.EMPTY).trim();
        SecurityContextUtils.setSecurityContext(StringUtils.equalsIgnoreCase("signed", jwtConfig.getType()) ? validate(token) : decryptValidating(token));
        chain.doFilter(request, response);
    }

    @SneakyThrows
    private SignedJWT decryptValidating(String encryptedToken) {
        String signedToken = tokenConverter.decryptToken(encryptedToken);
        return validate(signedToken);
    }

    @SneakyThrows
    private SignedJWT validate(String signedToken) {
        tokenConverter.validateTokenSignature(signedToken);
        return SignedJWT.parse(signedToken);
    }

}
