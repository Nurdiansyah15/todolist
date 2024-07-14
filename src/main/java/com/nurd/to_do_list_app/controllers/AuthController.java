package com.nurd.to_do_list_app.controllers;

import com.nurd.to_do_list_app.services.AuthService;
import com.nurd.to_do_list_app.utils.dto.AuthDto;
import com.nurd.to_do_list_app.utils.response.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDto.RequestLoginDto registerDto) {
        return ResponseBuilder.renderJSON(authService.login(registerDto), "user logged in successfully", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid AuthDto.RequestRegisterDto requestRegisterDto) {
        return ResponseBuilder.renderJSON(authService.register(requestRegisterDto), "user registered successfully", HttpStatus.CREATED);
    }
}
