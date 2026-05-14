package com.hotel.ms_autenticacion.repository;
import com.hotel.ms_autenticacion.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Este método es oro puro: nos permitirá buscar al usuario en la BD cuando intente loguearse
    Optional<Usuario> findByUsername(String username);
}
