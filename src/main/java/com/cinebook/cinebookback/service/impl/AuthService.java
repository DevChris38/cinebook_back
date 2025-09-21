package com.cinebook.cinebookback.service.impl;

import com.cinebook.cinebookback.DTO.AccountResponseDTO;
import com.cinebook.cinebookback.DTO.LoginRequestDTO;
import com.cinebook.cinebookback.DTO.RegisterRequestDTO;
import com.cinebook.cinebookback.entity.*;
import com.cinebook.cinebookback.repository.*;
import com.cinebook.cinebookback.security.CustomPasswordEncoder;
import com.cinebook.cinebookback.configuration.SecurityConfig;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
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
    private final JobRepository jobRepository;
    private final ImageRepository imageRepository;
    private final RegionRepository regionRepository;
    private final JwtService jwtService;

    public ResponseEntity<AccountResponseDTO> register(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(AccountResponseDTO.builder()
                    .message("Un utilisateur possède déjà ce nom d'utilisateur")
                    .build());
        } else {
            Set<Role> roles = new HashSet<>();
            /*for (String role : registerRequestDTO.getRole()) {
                roles.add(roleRepository.findByRoleName(role));
            }*/
            roles.add(roleRepository.findByRoleName(String.valueOf(RoleName.USER)));

            Set<Job> jobs = new HashSet<>();
            for (String job : registerRequestDTO.getJobs()) {
                if (jobRepository.findByCode(job).isPresent()) {
                    jobs.add(jobRepository.findByCode(job).get());
                }
            }

            Set<Region> regions = new HashSet<>();
            for (String region : registerRequestDTO.getRegions()) {
                if (regionRepository.findByCode(region).isPresent()) {
                    regions.add(regionRepository.findByCode(region).get());
                }
            }

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = now.format(formatter);

            Image image = new Image(registerRequestDTO.getPath(), registerRequestDTO.getImgProfil());

            imageRepository.save(image);

            var user = User.builder()
                    .username(registerRequestDTO.getUsername())
                    .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                    .roles(roles)
                    .imgProfil(image)
                    .firstname(registerRequestDTO.getFirstname())
                    .lastname(registerRequestDTO.getLastname())
                    .sexe(registerRequestDTO.getSexe())
                    .phone(registerRequestDTO.getPhone())
                    .email(registerRequestDTO.getEmail())
                    .inscriptionDate(formattedDate)
                    .isPremium(false)
                    .jobs(jobs)
                    .regions(regions)
                    .build();


            userRepository.save(user);

            return ResponseEntity.ok().body(AccountResponseDTO.builder()
                    .message("L'utilisateur a bien été créé")
                    .build());
        }

    }

    public ResponseEntity<AccountResponseDTO> loginbyUsername(LoginRequestDTO loginRequestDTO) {
        var user = userRepository.findByUsername(loginRequestDTO.getUsername());
        if (user.isPresent()) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            var jwtToken = jwtService.generateToken(user.get());

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Acces-Control-Expose-Headers", "Authorization");
            responseHeaders.add("Authorization", "Bearer " + jwtToken);

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(AccountResponseDTO.builder()
                            .message("Utilisateur authentifié avec succès")
                            .userName(loginRequestDTO.getUsername())
                            .imgProfil(user.get().getImgProfil().getLink())
                            .build());
        } else {
            return this.loginbyEmail(loginRequestDTO);
        }
    }

    public ResponseEntity<AccountResponseDTO> loginbyEmail(LoginRequestDTO loginRequestDTO) {
        try {
            var user = userRepository.findByEmail(loginRequestDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(),
                            loginRequestDTO.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            var jwtToken = jwtService.generateToken(user);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Access-Control-Expose-Headers", "Authorization");
            responseHeaders.add("Authorization", "Bearer " + jwtToken);

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(AccountResponseDTO.builder()
                            .message("User authenticated successfully")
                            .userName(user.getUsername())
                            .imgProfil(user.getImgProfil().getLink())
                            .build());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(AccountResponseDTO.builder()
                            .message("User not found")
                            .build());
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(AccountResponseDTO.builder()
                            .message("Invalid credentials")
                            .build());
        }
    }
}
