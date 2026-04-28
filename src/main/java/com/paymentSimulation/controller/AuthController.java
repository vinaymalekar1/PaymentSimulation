package com.paymentSimulation.controller;

import com.paymentSimulation.dto.AuthRequest;
import com.paymentSimulation.dto.AuthResponse;
import com.paymentSimulation.entity.User;
import com.paymentSimulation.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        User user = authService.login(request);

        String token = "dummy-token-for-" + user.getUsername();

        return new AuthResponse(token, "Login successful");
    }
}
