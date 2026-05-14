package com.hotel.ms_autenticacion.service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    // Clave secreta generada automáticamente para firmar los tokens de forma segura
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Tiempo de expiración del token: 1 hora (3600000 milisegundos)
    private static final long EXPIRATION_TIME = 3600000;

    public String generarToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // Añadimos el rol del usuario como un "Claim"
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY) // Firmamos el token con nuestra clave secreta
                .compact();
    }
}
