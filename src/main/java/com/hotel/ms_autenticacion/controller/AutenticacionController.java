package com.hotel.ms_autenticacion.controller;
import com.hotel.ms_autenticacion.model.Usuario;
import com.hotel.ms_autenticacion.repository.UsuarioRepository;
import com.hotel.ms_autenticacion.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/autenticacion")
@Tag(name = "Autenticación", description = "Endpoints para el inicio de sesión y generación de tokens de seguridad (JWT)")
public class AutenticacionController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    // --- ANOTACIONES SWAGGER ---
    @Operation(summary = "Iniciar sesión", description = "Recibe username y password. Valida contra la base de datos y, si es correcto, devuelve un Token JWT firmado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso, devuelve el Token JWT"),
            @ApiResponse(responseCode = "401", description = "No autorizado (Credenciales inválidas)")
    })

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        //  Buscamos al usuario real en la base de datos
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);

        //  Validamos que exista y que la contraseña enviada coincida
        if (usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(password)) {
            Usuario usuario = usuarioOpt.get();

            //  Generamos el token firmado usando nuestra "Fábrica"
            String token = jwtService.generarToken(usuario.getUsername(), usuario.getRole());

            //  Retornamos el token al cliente (Postman)
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}