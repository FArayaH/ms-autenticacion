package com.hotel.ms_autenticacion.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role; // para los roles pueden ser ROLE_ADMIN, ROLE_USER por ejemplop

}
