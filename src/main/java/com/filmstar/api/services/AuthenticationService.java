package com.filmstar.api.services;

import com.filmstar.api.dtos.requests.SignUpRequest;
import com.filmstar.api.dtos.requests.SigninRequest;
import com.filmstar.api.dtos.responses.JwtAuthenticationResponse;

public interface AuthenticationService {
	
	/** REGISTRO */
    JwtAuthenticationResponse signup(SignUpRequest request);
    /** ACCESO a Token JWT */
    JwtAuthenticationResponse signin(SigninRequest request);
}
