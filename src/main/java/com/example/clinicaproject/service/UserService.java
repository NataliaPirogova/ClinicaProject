package com.example.clinicaproject.service;

import com.example.clinicaproject.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User findByName(String name);

    void save(User user);
}
