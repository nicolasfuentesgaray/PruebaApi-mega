package com.api.prueba.services;

import com.api.prueba.dtos.LoginUsuarioDto;
import com.api.prueba.dtos.RegistroUsuarioDto;
import com.api.prueba.entities.Rol;
import com.api.prueba.entities.RolEnum;
import com.api.prueba.entities.Usuario;
import com.api.prueba.repositories.RolRepository;
import com.api.prueba.repositories.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public LoginService(
        UsuarioRepository usuarioRepository,
        RolRepository rolRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarse(RegistroUsuarioDto input) {
        Optional<Rol> optionalRole = rolRepository.findByNombre(RolEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new Usuario().setNombre(input.getNombre())
            .setEmail(input.getEmail())
            .setPassword(passwordEncoder.encode(input.getPassword()))
            .setRole(optionalRole.get());

        return usuarioRepository.save(user);
    }

    public Usuario authenticate(LoginUsuarioDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
            )
        );

        return usuarioRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public List<Usuario> allUsers() {
        List<Usuario> users = new ArrayList<>();

        usuarioRepository.findAll().forEach(users::add);

        return users;
    }
}
