package com.example.ham.service;

import com.example.ham.model.Product;
import com.example.ham.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    // READ all
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    // READ one
    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    // CREATE
    public Product createProduct(Product product) {
        // id null -> insert, createdAt/active seront remplis par @PrePersist
        return repo.save(product);
    }

    // UPDATE
    public Product updateProduct(int id, Product productData) {
        Product existing = repo.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        // On met à jour les champs modifiables
        existing.setName(productData.getName());
        existing.setDescription(productData.getDescription());
        existing.setPrice(productData.getPrice());
        existing.setQuantity(productData.getQuantity());
        existing.setActive(productData.getActive());

        return repo.save(existing);
    }

    // DELETE
    public boolean deleteProduct(int id) {
        Product existing = repo.findById(id).orElse(null);
        if (existing == null) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
