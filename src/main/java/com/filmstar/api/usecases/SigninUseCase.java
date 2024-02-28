package com.filmstar.api.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.filmstar.api.dtos.requests.SigninRequest;
import com.filmstar.api.dtos.responses.JwtAuthenticationResponse;
import com.filmstar.api.services.AuthenticationService;

@Component
public class SigninUseCase {
	
	private final AuthenticationService authenticationService;
	
	public SigninUseCase(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;		
	}

    public ResponseEntity<JwtAuthenticationResponse> execute(SigninRequest signinRequest) {
        return new ResponseEntity<JwtAuthenticationResponse>(authenticationService.signin(signinRequest), HttpStatus.ACCEPTED);
    }

}
