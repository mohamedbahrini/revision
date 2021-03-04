package com.example.revision.service;

import com.example.revision.model.Role;
import com.example.revision.model.RoleName;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface IRoleService {

    Role saveRole(Role role);

    List<Role> getAllRoles();

    Optional<Role> getRoleByName(RoleName roleUser);
}
