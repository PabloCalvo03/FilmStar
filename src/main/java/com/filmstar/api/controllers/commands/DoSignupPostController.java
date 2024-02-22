package com.filmstar.api.controllers.commands;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.requests.SignUpRequest;
import com.filmstar.api.dtos.responses.JwtAuthenticationResponse;
import com.filmstar.api.services.AuthenticationService;

/**
 * Controlador para el registro de los usuarios.
 */
@RestController
@RequestMapping("/api/v1/auth/signup")
public class DoSignupPostController {
	
	private final AuthenticationService authenticationService;
	
	public DoSignupPostController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;		
	}

    /**
     * Maneja la solicitud de registro de un nuevo usuario.
     *
     * @param request Datos de registro del nuevo usuario.
     * @return Respuesta con token JWT y detalles del usuario recién registrado.
     */
    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

}
