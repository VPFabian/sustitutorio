package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Get a single product by name
    @GetMapping("/{name}")
    public Product getProductByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        repository.save(product);
        return ResponseEntity.status(201).body("Producto creado correctamente");
    }

    // Update an existing product by name
    @PutMapping("/{name}")
    public ResponseEntity<String> updateProductByName(@PathVariable String name, @RequestBody Product updatedProduct) {
        Product product = repository.findByName(name);
        if (product == null) {
            return ResponseEntity.status(404).body("Producto no encontrado");
        }

        product.setName(updatedProduct.getName());
        product.setStock(updatedProduct.getStock());
        product.setPrice(updatedProduct.getPrice());

        repository.save(product);

        return ResponseEntity.ok("Producto actualizado correctamente");
    }

    // Delete a product by name
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteProductByName(@PathVariable String name) {
        Product product = repository.findByName(name);
        if (product == null) {
            return ResponseEntity.status(404).body("Producto no encontrado");
        }

        // Elimina el producto por nombre
        repository.deleteByName(name);

        return ResponseEntity.status(204).body("Producto eliminado correctamente");
    }

}
