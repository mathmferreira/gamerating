package com.example.gamerating.config;

import com.example.gamerating.domain.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class GameRatingAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (isNotAuthenticated(auth)) {
            return Optional.of("system");
        }

        User user = (User) auth.getPrincipal();
        return Optional.ofNullable(user.getEmail());
    }

    private boolean isNotAuthenticated(Authentication auth) {
        return Objects.isNull(auth) || !auth.isAuthenticated() || auth.getClass() == AnonymousAuthenticationToken.class;
    }
    
}
