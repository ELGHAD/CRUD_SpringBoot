package com.example.ham.service;

import com.example.ham.model.User;
import com.example.ham.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // READ all
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // READ one
    public User getUserById(int id) {
        return repo.findById(id);
    }

    // CREATE
    public User createUser(User user) {
        // on laisse l'id à null, le repo va le générer
        user.setId(null);
        return repo.save(user);
    }

    // UPDATE
    public User updateUser(int id, User user) {
        User existing = repo.findById(id);
        if (existing == null) {
            return null;
        }
        existing.setName(user.getName());
        return repo.save(existing);
    }

    // DELETE
    public boolean deleteUser(int id) {
        User existing = repo.findById(id);
        if (existing == null) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
