package com.example.ham.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    // 1 User -> N Products
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Helpers pour gérer la relation
    public void addProduct(Product product) {
        products.add(product);
        product.setOwner(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setOwner(null);
    }

    // Getters / setters

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
