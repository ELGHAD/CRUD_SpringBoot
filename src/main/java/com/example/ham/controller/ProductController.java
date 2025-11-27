package com.example.ham.controller;

import com.example.ham.model.Product;
import com.example.ham.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // ========================
    // CRUD standard /products
    // ========================

    // READ all : GET /products
    @GetMapping("/products")
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    // READ one : GET /products/{id}
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getOne(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // CREATE simple : POST /products
    @PostMapping("/products")
    public Product create(@RequestBody Product product) {
        return service.createProduct(product);
    }

    // UPDATE : PUT /products/{id}
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> update(@PathVariable int id,
                                          @RequestBody Product product) {
        Product updated = service.updateProduct(id, product);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE : DELETE /products/{id}
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean deleted = service.deleteProduct(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // ==============================
    // Endpoints liés à un User
    // ==============================

    // GET /users/{userId}/products : tous les produits d'un user
    @GetMapping("/users/{userId}/products")
    public List<Product> getProductsByUser(@PathVariable int userId) {
        return service.getProductsByUser(userId);
    }

    // POST /users/{userId}/products : créer un produit pour un user
    @PostMapping("/users/{userId}/products")
    public ResponseEntity<Product> createForUser(@PathVariable int userId,
                                                 @RequestBody Product product) {
        Product created = service.createProductForUser(userId, product);
        if (created == null) {
            return ResponseEntity.notFound().build(); // user introuvable
        }
        return ResponseEntity.ok(created);
    }
}
