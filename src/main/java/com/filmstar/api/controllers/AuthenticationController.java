package com.filmstar.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.requests.SignUpRequest;
import com.filmstar.api.dtos.requests.SigninRequest;
import com.filmstar.api.dtos.responses.JwtAuthenticationResponse;
import com.filmstar.api.services.AuthenticationService;
import com.filmstar.api.services.UserService;

/**
 * Controlador que gestiona las operaciones de registro y inicio de sesión.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
    @Autowired
    AuthenticationService authenticationService;
    
    @Autowired
    UserService userService;

    /**
     * Maneja la solicitud de registro de un nuevo usuario.
     *
     * @param request Datos de registro del nuevo usuario.
     * @return Respuesta con token JWT y detalles del usuario recién registrado.
     */
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }
    
    /**
     * Maneja la solicitud de inicio de sesión de un usuario existente.
     *
     * @param request Datos de inicio de sesión del usuario.
     * @return Respuesta con token JWT y detalles del usuario autenticado.
     */
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
   
}