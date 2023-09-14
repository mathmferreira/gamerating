package com.example.gamerating.config.security;

import com.example.gamerating.domain.model.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Slf4j
public class SecurityContextUtils {

    private SecurityContextUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static void setSecurityContext(SignedJWT signedJWT) {
        try {
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            String username = claims.getSubject();
            if (StringUtils.isBlank(username)) {
                throw new JOSEException("Username missing from JWT");
            }
            List<String> authorities = claims.getStringListClaim("authorities");
            User user = User.builder()
                    .id(claims.getLongClaim("userId"))
                    .email(username)
                    .build();
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, createAuthorities(authorities));
            auth.setDetails(signedJWT.serialize());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception ex) {
            log.error(">>> Error setting security context ", ex);
            SecurityContextHolder.clearContext();
        }
    }

    private static List<SimpleGrantedAuthority> createAuthorities(List<String> authorities) {
        return authorities.stream().map(SimpleGrantedAuthority::new).toList();
    }

}
