package com.example.gamerating.config.security;


import com.example.gamerating.domain.model.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class TokenService {

    private final JwtConfig jwtConfig;

    @SneakyThrows
    public SignedJWT createSignedJWT(Authentication auth) {
        log.debug(">>> Starting to create the signed JWT");
        User user = (User) auth.getPrincipal();
        JWTClaimsSet jwtClaimsSet = this.createJWTClaimsSet(auth, user);
        KeyPair keyPair = this.generateKeyPair();
        log.debug(">>> Building JWK from the RSA Keys");
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        JWK jwk = new RSAKey.Builder(rsaPublicKey).keyID(UUID.randomUUID().toString()).build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256)
                .jwk(jwk)
                .type(JOSEObjectType.JWT)
                .build(), jwtClaimsSet);
        log.debug(">>> Signing the token with private RSA Key");
        RSASSASigner signer = new RSASSASigner(keyPair.getPrivate());
        signedJWT.sign(signer);

        log.debug(">>> Serialized token '{}'", signedJWT.serialize());
        return signedJWT;
    }

    public String encryptToken(SignedJWT signedJWT) throws JOSEException {
        log.debug(">>> Starting the encryptToken method");
        DirectEncrypter directEncrypter = new DirectEncrypter(jwtConfig.getPrivateKey().getBytes());

        JWEObject jweObject = new JWEObject(new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256)
                .contentType("JWT")
                .build(), new Payload(signedJWT));

        log.debug(">>> Encrypting token with system's private key");
        jweObject.encrypt(directEncrypter);

        log.debug(">>> Token encrypted");
        return jweObject.serialize();
    }

    private JWTClaimsSet createJWTClaimsSet(Authentication auth, User user) {
        log.debug(">>> Creating the JWTClaimsSet Object for '{}'", user);
        return new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .claim("authorities", auth.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .claim("userId", user.getId())
                .issuer("http://www.astro.com")
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + (jwtConfig.getExpiration() * 1000L)))
                .build();
    }

    @SneakyThrows
    private KeyPair generateKeyPair() {
        log.debug(">>> Generating RSA 2048 bits Keys");
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.genKeyPair();
    }

}
