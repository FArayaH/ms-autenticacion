package com.hotel.ms_autenticacion.service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // 1. Contraseña fija y larga (mínimo 32 caracteres para seguridad HS256)
    private static final String SECRET = "hotel-microservicios-secret-key-2026-segura";

    // 2. Clave generada a partir de la contraseña fija (Ya no es aleatoria)
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    // Tiempo de expiración del token: 1 hora (3600000 milisegundos)
    private static final long EXPIRATION_TIME = 3600000;

    public String generarToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }
}
