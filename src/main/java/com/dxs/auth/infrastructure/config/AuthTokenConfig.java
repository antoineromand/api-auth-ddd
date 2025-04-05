package com.dxs.auth.infrastructure.config;

import com.dxs.auth.core.external.TokenManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
public class AuthTokenConfig {

    @Value("${PRIVATE_KEY_PATH}")
    private String privateKeyPath;

    @Value("${PUBLIC_KEY_PATH}")
    private String publicKeyPath;

    @Bean
    public TokenManager tokenManager() throws Exception {
        PrivateKey privateKey = loadPrivateKey(Paths.get(privateKeyPath));
        PublicKey publicKey = loadPublicKey(Paths.get(publicKeyPath));
        return new TokenManager(publicKey, privateKey);
    }

    private PrivateKey loadPrivateKey(Path path) throws Exception {
        String pem = Files.readString(path);
        String base64 = pem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] keyBytes = java.util.Base64.getDecoder().decode(base64);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

    private PublicKey loadPublicKey(Path path) throws Exception {
        String pem = Files.readString(path);
        String base64 = pem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] keyBytes = java.util.Base64.getDecoder().decode(base64);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }
}

