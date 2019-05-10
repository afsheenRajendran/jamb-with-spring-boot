package com.ithellam.boot.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ithellam.boot.exceptions.ProductNotFoundException;
import com.ithellam.boot.model.Product;
import com.ithellam.boot.repository.IProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductRepository productRepository;

    @GetMapping
    public Iterable findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/name/{name}")
    public List<Product> findByName(@PathVariable String name) {
        return productRepository.findByName(name);
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
