package com.api.prueba.services;

import com.api.prueba.entities.Rol;
import com.api.prueba.entities.RolEnum;
import com.api.prueba.entities.Usuario;
import com.api.prueba.dtos.RegistroUsuarioDto;
import com.api.prueba.repositories.RolRepository;
import com.api.prueba.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> todosUsuarios() {
        List<Usuario> users = new ArrayList<>();

        usuarioRepository.findAll().forEach(users::add);

        return users;
    }

    public Usuario crearAdministrador(RegistroUsuarioDto input) {
        Optional<Rol> optionalRole = rolRepository.findByNombre(RolEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new Usuario()
                .setNombre(input.getNombre())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get());

        return usuarioRepository.save(user);
    }
}
