package com.example.revision.init;

import com.example.revision.model.Role;
import com.example.revision.model.RoleName;
import com.example.revision.model.User;
import com.example.revision.service.IRoleService;
import com.example.revision.service.IUserService;
import com.example.revision.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitDatabase implements CommandLineRunner {
    private IRoleService roleService;
    private IUserService userService;

    @Autowired
    public InitDatabase(IRoleService roleService, IUserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<RoleName> roles = Arrays.asList(RoleName.ROLE_ADMIN, RoleName.ROLE_USER);
        createRoles(roles);
        createUser();
    }

    void createRoles(List<RoleName> roleNames) {
        roleNames.forEach(roleName -> roleService.saveRole(new Role(roleName)));
    }

    void createUser() {
        User user = new User();
        user.setUsername("mohamed");
        user.setFirstname("Mohamed");
        user.setLastname("BAHRINI");
        user.setEmail("email@gmail.com");
        user.setPassword("12345");
        user.setEnabled(true);
        user.setRoles(roleService.getAllRoles().stream().collect(Collectors.toSet()));

        userService.saveUser(user);
    }

}
