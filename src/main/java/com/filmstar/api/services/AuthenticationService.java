package com.filmstar.api.services;

import com.filmstar.api.dtos.requests.SignupRequest;
import com.filmstar.api.dtos.requests.SigninRequest;
import com.filmstar.api.dtos.responses.JwtAuthenticationResponse;

public interface AuthenticationService {
	
    JwtAuthenticationResponse signup(SignupRequest request);
    JwtAuthenticationResponse signin(SigninRequest request);
}
