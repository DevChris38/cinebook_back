package com.cinebook.cinebookback.controller;

import com.cinebook.cinebookback.DTO.AccountResponseDTO;
import com.cinebook.cinebookback.DTO.UserFilterRequestDTO;
import com.cinebook.cinebookback.DTO.UserUpdateDTO;
import com.cinebook.cinebookback.entity.User;
import com.cinebook.cinebookback.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cinebook.cinebookback.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserDemoController {

    private final UserService userService;

    @GetMapping("/hello")
    public UserDTO hello(@RequestHeader("Authorization") String authorizationHeader) {
        return userService.getUserInformations(authorizationHeader);
    }

    @PostMapping("/update")
    public UserDTO update(
            @RequestBody UserUpdateDTO userUpdateDTO,
            @RequestHeader("Authorization") String authorizationHeader) {
        System.out.println("coucou");
        return userService.postUserInformations(authorizationHeader, userUpdateDTO);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<UserDTO>> getUsersWithFilters(@RequestBody UserFilterRequestDTO filter) {
        List<UserDTO> users = userService.getUsersWithFilters(filter);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/data")
    public ResponseEntity<UserDTO> getUserData(@RequestHeader("Authorization") String authorizationHeader) {
        UserDTO user = userService.getUserData(authorizationHeader);
        return ResponseEntity.ok(user);
    }
}
