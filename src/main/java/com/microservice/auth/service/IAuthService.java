package com.microservice.auth.service;

import com.microservice.auth.dto.AuthResponse;
import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;

import java.text.ParseException;

public interface IAuthService {

    AuthResponse login (LoginRequest loginRequest) ;

    AuthResponse register(RegisterRequest registerRequest) throws ParseException;
}
