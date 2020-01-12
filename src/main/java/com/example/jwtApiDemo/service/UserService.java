package com.example.jwtApiDemo.service;

import com.example.jwtApiDemo.models.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User register(User user);

    User findById(int id);

    User findByUsername(String username);

    void delete(int id);


}
