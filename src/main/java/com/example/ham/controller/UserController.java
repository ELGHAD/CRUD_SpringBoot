package com.example.ham.controller;

import com.example.ham.model.User;
import com.example.ham.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // READ all : GET /users
    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }

    // READ one : GET /users/1
    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable int id) {
        User user = service.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // CREATE : POST /users
    @PostMapping
    public User create(@RequestBody User user) {
        System.out.println(">>> POST /users, name = " + user.getName());
        return service.createUser(user);
    }

    // UPDATE : PUT /users/1
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable int id,
                                       @RequestBody User user) {
        User updated = service.updateUser(id, user);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE : DELETE /users/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean deleted = service.deleteUser(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
