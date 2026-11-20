
package com.example.api_tareas.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY = "EstaEsUnaClaveSecretaParaMiApiTareas2026";

    private final SecretKey key = Keys.hmacShaKeyFor(
        SECRET_KEY.getBytes(StandardCharsets.UTF_8)
    );

    public String generarToken(String correo) {

        return Jwts.builder()
            .subject(correo)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(key)
            .compact();
    }
public String validarToken(String token) {

    return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
}
}