package com.api.prueba.controllers;

import com.api.prueba.responses.LoginResponse;
import com.api.prueba.entities.Usuario;
import com.api.prueba.dtos.LoginUsuarioDto;
import com.api.prueba.dtos.RegistroUsuarioDto;
import com.api.prueba.services.LoginService;
import com.api.prueba.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class LoginController {
    private final JwtService jwtService;
    private final LoginService loginService;

    public LoginController(JwtService jwtService, LoginService loginService) {
        this.jwtService = jwtService;
        this.loginService = loginService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registro(@RequestBody RegistroUsuarioDto registroUsuarioDto) {
        Usuario registroUsuario = loginService.registrarse(registroUsuarioDto);

        return ResponseEntity.ok(registroUsuario);
    }

    @PostMapping("/ingresar")
    public ResponseEntity<LoginResponse> ingresar(@RequestBody LoginUsuarioDto loginUserDto) {
        Usuario usuario = loginService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(usuario);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime()).SetId(usuario.getId());

        return ResponseEntity.ok(loginResponse);
    }
}