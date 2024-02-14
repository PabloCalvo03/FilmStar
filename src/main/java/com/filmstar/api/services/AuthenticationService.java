package com.filmstar.api.services;

import com.filmstar.api.dtos.requests.SignUpRequest;
import com.filmstar.api.dtos.requests.SigninRequest;
import com.filmstar.api.dtos.responses.JwtAuthenticationResponse;

public interface AuthenticationService {
	
    JwtAuthenticationResponse signup(SignUpRequest request);
    JwtAuthenticationResponse signin(SigninRequest request);
}
