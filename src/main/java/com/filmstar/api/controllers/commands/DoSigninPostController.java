package com.filmstar.api.controllers.commands;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmstar.api.dtos.requests.SigninRequest;
import com.filmstar.api.dtos.responses.JwtAuthenticationResponse;
import com.filmstar.api.usecases.SigninUseCase;

/**
 * Controlador para manejar las solicitudes de inicio de sesión a través de solicitudes POST.
 * Las solicitudes se gestionan mediante endpoints RESTful.
 */
@RestController
@RequestMapping("/api/v1/auth/signin")
public class DoSigninPostController {
    
    private final SigninUseCase signinUseCase;
    
    /**
     * Constructor que inyecta una instancia de SigninUseCase.
     *
     * @param signinUseCase Caso de uso para el inicio de sesión.
     */
    public DoSigninPostController(SigninUseCase signinUseCase) {
        this.signinUseCase = signinUseCase;        
    }

    /**
     * Maneja las solicitudes POST para iniciar sesión.
     *
     * @param signinRequest Objeto que contiene la solicitud de inicio de sesión.
     * @return ResponseEntity con información sobre el resultado de la operación de inicio de sesión.
     */
    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> execute(@RequestBody SigninRequest signinRequest) {
        ResponseEntity<JwtAuthenticationResponse> response = signinUseCase.execute(signinRequest);
        return response;
    }
}
