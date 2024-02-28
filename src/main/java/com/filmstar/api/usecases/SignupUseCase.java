package com.filmstar.api.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.filmstar.api.dtos.requests.SignupRequest;
import com.filmstar.api.dtos.responses.JwtAuthenticationResponse;
import com.filmstar.api.services.AuthenticationService;

@Component
public class SignupUseCase {

private final AuthenticationService authenticationService;
	
	public SignupUseCase(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;		
	}

    public ResponseEntity<JwtAuthenticationResponse> execute(SignupRequest signupRequest) {
        return new ResponseEntity<JwtAuthenticationResponse>(authenticationService.signup(signupRequest), HttpStatus.ACCEPTED);
    }
}
