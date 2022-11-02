package com.example.clinicaproject.service.impl;

import com.example.clinicaproject.model.User;
import com.example.clinicaproject.repository.UserRepository;
import com.example.clinicaproject.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public User findByName(String name) {
        return userRepo.findByUsername(name);
    }

    @Override
    public void save(User user) {
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

}
