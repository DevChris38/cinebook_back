package com.cinebook.cinebookback.service.impl;

import com.cinebook.cinebookback.DTO.UserUpdateDTO;
import com.cinebook.cinebookback.dto.UserDTO;
import com.cinebook.cinebookback.entity.User;
import com.cinebook.cinebookback.mapper.UserMapper;
import com.cinebook.cinebookback.repository.JobRepository;
import com.cinebook.cinebookback.repository.RegionRepository;
import com.cinebook.cinebookback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final JobRepository jobRepository;
    private final RegionRepository regionRepository;

    public UserDTO getUserInformations(String authorizationHeader) {
        String username = jwtService.extractUserName(authorizationHeader.substring(7));
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return UserMapper.toDto(user.get());
        } else {
            return new UserDTO();
        }
    }

    public UserDTO postUserInformations(String authorizationHeader, UserUpdateDTO userUpdateDTO) {
        String username = jwtService.extractUserName(authorizationHeader.substring(7));
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        User userUpdated = userRepository.save(UserMapper.toEntityForUpdate(user, userUpdateDTO, userRepository, jobRepository, regionRepository));
        return UserMapper.toDto(userUpdated);
    }
}