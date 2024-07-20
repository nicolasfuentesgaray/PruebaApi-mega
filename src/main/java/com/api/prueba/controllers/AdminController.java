package com.api.prueba.controllers;

import com.api.prueba.dtos.RegistroUsuarioDto;
import com.api.prueba.entities.Usuario;
import com.api.prueba.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admins")
@RestController
public class AdminController {
    private final UsuarioService usuarioService;

    public AdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Usuario> crearAdministrador(@RequestBody RegistroUsuarioDto registroUsuarioDto) {
        Usuario crearAdmin = usuarioService.crearAdministrador(registroUsuarioDto);

        return ResponseEntity.ok(crearAdmin);
    }
}
