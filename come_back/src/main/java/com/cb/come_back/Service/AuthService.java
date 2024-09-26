package com.cb.come_back.Service;

import com.cb.come_back.Config.AuthRequest;
import com.cb.come_back.Config.AuthenticationResponse;
import com.cb.come_back.Config.JwtService;
import com.cb.come_back.Config.RegisterRequest;
import com.cb.come_back.Entity.Author;
import com.cb.come_back.Repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthorRepository authorRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {

        var author = Author.builder()
                .authorName(registerRequest.getAuthorName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phone(registerRequest.getPhone())
                .build();

        authorRepository.save(author);
        var jwtToken = jwtService.generateToken(author);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(), authRequest.getPassword()
                )
        );
        var author = authorRepository.findByEmail(authRequest.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(author);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
