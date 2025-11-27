package com.example.ham.controller;

import com.example.ham.model.Product;
import com.example.ham.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products") // toutes les routes commenceront par /products
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // READ all : GET /products
    @GetMapping
    public List<Product> getAll() {
        return service.getAllProducts();
    }

    // READ one : GET /products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // CREATE : POST /products
    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.createProduct(product);
    }

    // UPDATE : PUT /products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable int id,
                                          @RequestBody Product product) {
        Product updated = service.updateProduct(id, product);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE : DELETE /products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        boolean deleted = service.deleteProduct(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
