package com.hotel.ms_autenticacion.config;
import com.hotel.ms_autenticacion.model.Usuario;
import com.hotel.ms_autenticacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {

        // aca se verifica si la tabla de usuarios esta vacia
        if (usuarioRepository.count() == 0) {

            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword("1234"); // la puse para probar numas
            admin.setRole("ROLE_ADMIN");

            usuarioRepository.save(admin);

            System.out.println("DataInitializer: Usuario 'admin' creado con éxito en la BD.");
        } else {
            System.out.println("DataInitializer: La tabla de usuarios ya tiene datos.");
        }
    }
}
