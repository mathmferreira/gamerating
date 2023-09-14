package com.example.gamerating.config.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@Getter @Setter @ToString
@ConfigurationProperties(prefix = "jwt.config")
public class JwtConfig {

    private String loginUrl = "/login/**";
    private String type = "encrypted";
    private String privateKey = "jYCemjKRUUo4vOSVtjVB1uxnAqjkYaDU";
    private int expiration = 3600;

    @Getter @Setter
    public static class Header {
        private String name = HttpHeaders.AUTHORIZATION;
        private String prefix = "Bearer ";
    }

    @NestedConfigurationProperty
    private Header header = new Header();

}
