package com.api.prueba.bootstrap;

import com.api.prueba.dtos.RegistroUsuarioDto;
import com.api.prueba.entities.Rol;
import com.api.prueba.entities.RolEnum;
import com.api.prueba.entities.Usuario;
import com.api.prueba.repositories.RolRepository;
import com.api.prueba.repositories.UsuarioRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
        RolRepository rolRepository,
        UsuarioRepository usuarioRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.crearSuperAdministrador();
    }

    private void crearSuperAdministrador() {
        RegistroUsuarioDto registroUsuarioDto = new RegistroUsuarioDto();
        registroUsuarioDto.setNombre("Super Admin").setEmail("super.admin@email.com").setPassword("123456");

        Optional<Rol> optionalRol = rolRepository.findByNombre(RolEnum.SUPER_ADMIN);
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(registroUsuarioDto.getEmail());

        if (optionalRol.isEmpty() || optionalUsuario.isPresent()) {
            return;
        }

        var user = new Usuario().setNombre(registroUsuarioDto.getNombre())
            .setEmail(registroUsuarioDto.getEmail())
            .setPassword(passwordEncoder.encode(registroUsuarioDto.getPassword()))
            .setRole(optionalRol.get());

        usuarioRepository.save(user);
    }
}
