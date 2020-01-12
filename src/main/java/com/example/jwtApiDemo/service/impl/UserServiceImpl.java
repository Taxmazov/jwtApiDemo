package com.example.jwtApiDemo.service.impl;

import com.example.jwtApiDemo.models.Role;
import com.example.jwtApiDemo.models.Status;
import com.example.jwtApiDemo.models.User;
import com.example.jwtApiDemo.repository.RoleRepository;
import com.example.jwtApiDemo.repository.UserRepository;
import com.example.jwtApiDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public final RoleRepository roleRepository;
    public final UserRepository userRepository;
    public final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        return registeredUser;
    }

    @Override
    public User findById(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user=userRepository.findByUserName(username);
        return user;
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
