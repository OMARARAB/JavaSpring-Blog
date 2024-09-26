package com.cb.come_back.Controller;

import com.cb.come_back.Config.AuthRequest;
import com.cb.come_back.Config.AuthenticationResponse;
import com.cb.come_back.Config.RegisterRequest;
import com.cb.come_back.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

}
