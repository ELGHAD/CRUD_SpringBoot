package com.example.ham.repository;

import com.example.ham.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();
    private int currentId = 1; // auto-increment

    public List<User> findAll() {
        return users;
    }

    public User findById(Integer id) {
        if (id == null) return null;
        return users.stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst()
                .orElse(null);
    }

    public User save(User user) {
        if (user.getId() == null) {
            // CREATE
            user.setId(currentId++);
            users.add(user);
        } else {
            // UPDATE
            for (int i = 0; i < users.size(); i++) {
                if (user.getId().equals(users.get(i).getId())) {
                    users.set(i, user);
                    return user;
                }
            }
            users.add(user);
        }
        return user;
    }

    public void deleteById(Integer id) {
        if (id == null) return;
        users.removeIf(u -> id.equals(u.getId()));
    }
}
