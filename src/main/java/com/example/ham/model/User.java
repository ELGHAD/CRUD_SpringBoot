package com.example.ham.model;

import jakarta.persistence.*;

@Entity                      // dit que c'est une entité JPA
@Table(name = "users")       // nom de la table dans MySQL
public class User {

    @Id                      // clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment MySQL
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
