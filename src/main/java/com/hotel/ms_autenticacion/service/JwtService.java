package com.hotel.ms_autenticacion.service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    // Clave secreta fija para compartir con los otros microservicios
    private static final String SECRET_KEY_STRING = "MiClaveSecretaSuperSeguraParaHotel1234567890";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

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
