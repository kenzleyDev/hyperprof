package br.com.luankenzley.hyperprof.api.auth.services;

import br.com.luankenzley.hyperprof.api.auth.dtos.LoginRequest;
import br.com.luankenzley.hyperprof.api.auth.dtos.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);
}
