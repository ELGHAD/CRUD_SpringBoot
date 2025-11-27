package com.example.ham.repository;

import com.example.ham.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Tu peux ajouter des méthodes custom plus tard, exemple :
    // List<Product> findByActiveTrue();
    // List<Product> findByPriceGreaterThan(Double minPrice);
}
