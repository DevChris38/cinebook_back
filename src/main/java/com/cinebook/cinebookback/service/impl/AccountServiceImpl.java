package com.cinebook.cinebookback.service.impl;

import com.cinebook.cinebookback.entity.Role;
import com.cinebook.cinebookback.entity.User;
import com.cinebook.cinebookback.repository.RoleRepository;
import com.cinebook.cinebookback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("cet utilisateur n'existe pas"));
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(User user, List<Role> roles) {
        User userFromDb = userRepository.findByUsername(user.getUsername()).orElse(null);
        roles.stream()
                .map(Role::getRoleName)
                .map(roleRepository::findByRoleName)
                .forEach(userFromDb.getRoles()::add);
    }
}
