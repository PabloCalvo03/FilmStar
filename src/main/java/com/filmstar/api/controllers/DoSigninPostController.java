package com.filmstar.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.requests.SigninRequest;
import com.filmstar.api.dtos.responses.JwtAuthenticationResponse;
import com.filmstar.api.services.AuthenticationService;
/**
 * Controlador para el inicio de sesion de los usuarios.
 */
@RestController
@RequestMapping("/api/v1/auth/signin")
public class DoSigninPostController {
	
	private final AuthenticationService authenticationService;
		
	public DoSigninPostController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;		
	}

	/**
     * Maneja la solicitud de inicio de sesión de un usuario existente.
     *
     * @param request Datos de inicio de sesión del usuario.
     * @return Respuesta con token JWT y detalles del usuario autenticado.
     */
    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
	
}
