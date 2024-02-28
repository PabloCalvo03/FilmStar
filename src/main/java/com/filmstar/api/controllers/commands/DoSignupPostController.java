package com.filmstar.api.controllers.commands;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.requests.SignupRequest;
import com.filmstar.api.dtos.responses.JwtAuthenticationResponse;
import com.filmstar.api.usecases.SignupUseCase;

/**
 * Controlador para manejar las solicitudes de registro a través de solicitudes POST.
 * Las solicitudes se gestionan mediante endpoints RESTful.
 */
@RestController
@RequestMapping("/api/v1/auth/signup")
public class DoSignupPostController {
    
    private final SignupUseCase signupUseCase;
    
    /**
     * Constructor que inyecta una instancia de SignupUseCase.
     *
     * @param signupUseCase Caso de uso para el registro de usuarios.
     */
    public DoSignupPostController(SignupUseCase signupUseCase) {
        this.signupUseCase = signupUseCase;        
    }

    /**
     * Maneja las solicitudes POST para el registro de usuarios.
     *
     * @param signupRequest Objeto que contiene la solicitud de registro.
     * @return ResponseEntity con información sobre el resultado de la operación de registro.
     */
    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> execute(@RequestBody SignupRequest signupRequest) {
        ResponseEntity<JwtAuthenticationResponse> response = signupUseCase.execute(signupRequest);
        return response;
    }
}
