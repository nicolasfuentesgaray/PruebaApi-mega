package com.api.prueba.controllers;

import com.api.prueba.entities.Usuario;
import com.api.prueba.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService userService) {
        this.usuarioService = userService;
    }

    @GetMapping("/yo")
    @PreAuthorize("estaAutenticado()")
    public ResponseEntity<Usuario> estaAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Usuario currentUser = (Usuario) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<Usuario>> allUsers() {
        List <Usuario> users = usuarioService.todosUsuarios();

        return ResponseEntity.ok(users);
    }
}
