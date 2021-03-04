package com.example.revision.service;

import com.example.revision.model.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);

    List<User> getAllUsers();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findUsers(String search);
}
