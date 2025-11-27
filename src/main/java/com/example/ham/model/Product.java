package com.example.ham.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products") // table MySQL = products
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;           // identifiant auto-incrémenté

    @Column(nullable = false, length = 150)
    private String name;          // nom du produit

    @Column(length = 500)
    private String description;   // description optionnelle

    @Column(nullable = false)
    private Double price;         // prix

    @Column(nullable = false)
    private Integer quantity;     // stock

    @Column(nullable = false)
    private Boolean active;       // produit actif ou non

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // date de création (automatique)

    public Product() {
    }

    public Product(Integer id, String name, String description,
                   Double price, Integer quantity, Boolean active,
                   LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.active = active;
        this.createdAt = createdAt;
    }

    // On remplit createdAt automatiquement avant INSERT
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (active == null) {
            active = true; // par défaut actif
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
