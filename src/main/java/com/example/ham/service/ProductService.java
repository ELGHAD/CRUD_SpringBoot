package com.example.ham.service;

import com.example.ham.model.Product;
import com.example.ham.model.User;
import com.example.ham.repository.ProductRepository;
import com.example.ham.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public ProductService(ProductRepository productRepo, UserRepository userRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    // READ all
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // READ one
    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null);
    }

    // CREATE simple (sans user, si tu veux le garder)
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    // CREATE product pour un user donné
    public Product createProductForUser(int userId, Product product) {
        User owner = userRepo.findById(userId).orElse(null);
        if (owner == null) {
            return null; // on gérera ça en 404 côté controller
        }
        product.setOwner(owner);
        return productRepo.save(product);
    }

    // Lister tous les produits d'un user
    public List<Product> getProductsByUser(int userId) {
        return productRepo.findByOwnerId(userId);
    }

    // UPDATE
    public Product updateProduct(int id, Product productData) {
        Product existing = productRepo.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        existing.setName(productData.getName());
        existing.setDescription(productData.getDescription());
        existing.setPrice(productData.getPrice());
        existing.setQuantity(productData.getQuantity());
        existing.setActive(productData.getActive());

        // éventuellement on pourrait changer le owner aussi, mais on le laisse comme ça
        return productRepo.save(existing);
    }

    // DELETE
    public boolean deleteProduct(int id) {
        Product existing = productRepo.findById(id).orElse(null);
        if (existing == null) {
            return false;
        }
        productRepo.deleteById(id);
        return true;
    }
}
