package com.example.ham.repository;

import com.example.ham.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Tu peux ajouter des méthodes custom plus tard, exemple :

    List<Product> findByOwnerId(Integer ownerId);
    // List<Product> findByActiveTrue();
    // List<Product> findByPriceGreaterThan(Double minPrice);
}
