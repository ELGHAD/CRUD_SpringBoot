package com.example.ham.repository;

import com.example.ham.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Pas besoin d'écrire de méthode pour le CRUD de base,
    // JpaRepository te donne déjà :
    // findAll(), findById(), save(), deleteById(), etc.
}
