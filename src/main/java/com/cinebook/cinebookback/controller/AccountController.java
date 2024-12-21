package com.cinebook.cinebookback.controller;

import com.cinebook.cinebookback.DTO.AccountResponseDTO;
import com.cinebook.cinebookback.DTO.LoginRequestDTO;
import com.cinebook.cinebookback.DTO.RegisterRequestDTO;
import com.cinebook.cinebookback.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AuthService authService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/register")
    public ResponseEntity<AccountResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return authService.register(registerRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AccountResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return authService.loginbyUsername(loginRequestDTO);
    }
}
