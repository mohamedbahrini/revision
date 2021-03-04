package com.example.revision.controller;

import com.example.revision.dto.ApiResponse;
import com.example.revision.dto.SignUpRequest;
import com.example.revision.exception.ResourceNotFoundException;
import com.example.revision.model.Role;
import com.example.revision.model.RoleName;
import com.example.revision.model.User;
import com.example.revision.service.IRoleService;
import com.example.revision.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private IUserService userService;
    private IRoleService roleService;

    @Autowired
    public UserController(IUserService userService, IRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername()) || userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username or Email already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Role userRole = roleService.getRoleByName(RoleName.ROLE_USER).orElseThrow(() -> new ResourceNotFoundException("Role", "name", "ROLE_USER"));
        User user = new User(signUpRequest.getUsername(), signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getEmail(), signUpRequest.getPassword(), true, Collections.singleton(userRole));

        User result = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    // request example : ( username:mohamed  AND id:1 ) OR id:2
    @GetMapping("/find")
    public List<User> findUsers(@RequestParam String search){
        return userService.findUsers(search);
    }

    @GetMapping("/test")
    public String test() {
        return "success test";
    }
}
