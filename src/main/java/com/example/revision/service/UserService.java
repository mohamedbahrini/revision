package com.example.revision.service;

import com.example.revision.dao.UserRepository;
import com.example.revision.model.User;
import com.example.revision.specification.CriteriaParser;
import com.example.revision.specification.GenericSpecification;
import com.example.revision.specification.GenericSpecificationsBuilder;
import com.example.revision.specification.SpecSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.List;
import java.util.function.Function;

@Service
public class UserService implements IUserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> findUsers(String search) {
        Specification<User> specification = resolveSpecificationFromInfixExpr(search);
        return userRepository.findAll(specification);
    }

    protected Specification<User> resolveSpecificationFromInfixExpr(String searchParameters) {
        CriteriaParser parser = new CriteriaParser();
        GenericSpecificationsBuilder<User> specBuilder = new GenericSpecificationsBuilder<>();
        Deque<?> postFixedExprStack = parser.parse(searchParameters);
        Function<SpecSearchCriteria, Specification<User>> converter = GenericSpecification<User>::new;
        return specBuilder.build(postFixedExprStack, converter);
    }
}
