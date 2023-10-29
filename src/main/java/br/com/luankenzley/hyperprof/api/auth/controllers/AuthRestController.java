package br.com.luankenzley.hyperprof.api.auth.controllers;

import br.com.luankenzley.hyperprof.api.auth.dtos.LoginRequest;
import br.com.luankenzley.hyperprof.api.auth.dtos.LoginResponse;
import br.com.luankenzley.hyperprof.api.auth.services.AuthService;
import br.com.luankenzley.hyperprof.api.common.routes.ApiRoutes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping(ApiRoutes.LOGIN)
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
