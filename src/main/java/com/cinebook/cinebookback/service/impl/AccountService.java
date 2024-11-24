package com.cinebook.cinebookback.service.impl;

import com.cinebook.cinebookback.entity.Role;
import com.cinebook.cinebookback.entity.User;

import java.util.List;

public interface AccountService {
    Role addNewRole(Role role);

    void addRoleToUser(User user, List<Role> role);
}
