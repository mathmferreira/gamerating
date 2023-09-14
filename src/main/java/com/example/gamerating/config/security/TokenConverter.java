package com.example.gamerating.config.security;

import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class TokenConverter {

    private final JwtConfig jwtConfig;

    @SneakyThrows
    public String decryptToken(String encryptedToken) {
        log.debug(">>> Decrypting token");
        JWEObject jweObject = JWEObject.parse(encryptedToken);
        DirectDecrypter directDecrypter = new DirectDecrypter(jwtConfig.getPrivateKey().getBytes());
        jweObject.decrypt(directDecrypter);
        log.debug(">>> Token decrypted, returning to signing token . . .");
        return jweObject.getPayload().toSignedJWT().serialize();
    }

    @SneakyThrows
    public void validateTokenSignature(String signedToken) {
        log.debug(">>> Starting method to validate token signature...");
        SignedJWT signedJWT = SignedJWT.parse(signedToken);
        log.debug(">>> Token Parsed! Retrieving public key from signed token");
        RSAKey publicKey = RSAKey.parse(signedJWT.getHeader().getJWK().toJSONObject());
        log.debug(">>> Public Key retrieved, validating signature");
        if (!signedJWT.verify(new RSASSAVerifier(publicKey))) {
            throw new AccessDeniedException("Invalid token signature");
        }
        log.debug(">>> The token has a valid signature");
    }


}
