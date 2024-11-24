package com.cinebook.cinebookback.service.impl;

import com.cinebook.cinebookback.DTO.AccountResponseDTO;
import com.cinebook.cinebookback.DTO.LoginRequestDTO;
import com.cinebook.cinebookback.DTO.RegisterRequestDTO;
import com.cinebook.cinebookback.entity.Role;
import com.cinebook.cinebookback.entity.User;
import com.cinebook.cinebookback.repository.RoleRepository;
import com.cinebook.cinebookback.repository.UserRepository;
import com.cinebook.cinebookback.security.CustomPasswordEncoder;
import com.cinebook.cinebookback.configuration.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    public final CustomPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SecurityConfig securityConfig;
    private final RoleRepository roleRepository;

    public ResponseEntity<AccountResponseDTO> register(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(AccountResponseDTO.builder()
                    .message("Un utilisateur possède déjà ce nom d'utilisateur")
                    .build());
        } else {
            Set<Role> roles = new HashSet<>();
            for (String role : registerRequestDTO.getRole()) {
                roles.add(roleRepository.findByRoleName(role));
            }


            var user = User.builder()
                    .username(registerRequestDTO.getUsername())
                    .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                    .roles(roles)
                    .build();

            userRepository.save(user);

            return ResponseEntity.ok().body(AccountResponseDTO.builder()
                    .message("L'utilisateur a bien été créé")
                    .build());
        }

    }

    public ResponseEntity<AccountResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword())
            );
            System.out.println("$$$$ auth : " + authentication);
        } catch (AuthenticationException e) {
            System.out.println("$$$$ err : " + e.getMessage());
        }
        return null;
    }
}
