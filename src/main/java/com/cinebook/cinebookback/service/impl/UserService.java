package com.cinebook.cinebookback.service.impl;

import com.cinebook.cinebookback.DTO.UserFilterRequestDTO;
import com.cinebook.cinebookback.DTO.UserUpdateDTO;
import com.cinebook.cinebookback.dto.UserDTO;
import com.cinebook.cinebookback.entity.Image;
import com.cinebook.cinebookback.entity.User;
import com.cinebook.cinebookback.entity.specification.UserSpecification;
import com.cinebook.cinebookback.mapper.UserMapper;
import com.cinebook.cinebookback.repository.ImageRepository;
import com.cinebook.cinebookback.repository.JobRepository;
import com.cinebook.cinebookback.repository.RegionRepository;
import com.cinebook.cinebookback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final JobRepository jobRepository;
    private final RegionRepository regionRepository;
    private final ImageRepository imageRepository;

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
        Image newImage = imageRepository.save(new Image(userUpdateDTO.getPath(), userUpdateDTO.getImgProfil()));
        User userUpdated = userRepository.save(UserMapper.toEntityForUpdate(user, userUpdateDTO, newImage, userRepository, jobRepository, regionRepository, imageRepository));
        return UserMapper.toDto(userUpdated);
    }

    public List<UserDTO> getUsersWithFilters(UserFilterRequestDTO filter) {
        List<User> userList = userRepository.findAll(UserSpecification.filterBy(filter));
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(UserMapper.toDto(user));
        }
        return userDTOList;
    }

    public UserDTO getUserData(String authorizationHeader) {
        String username = jwtService.extractUserName(authorizationHeader.substring(7));
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserMapper.toDto(user);
    }
}
