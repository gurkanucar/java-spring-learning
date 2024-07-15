package org.gucardev.mixed.relationship_cases.e_commerce.controller;

import org.gucardev.mixed.relationship_cases.e_commerce.dto.ProductDTO;
import org.gucardev.mixed.relationship_cases.e_commerce.entitiy.Product;
import org.gucardev.mixed.relationship_cases.e_commerce.mapper.ProductMapper;
import org.gucardev.mixed.relationship_cases.e_commerce.repo.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductController(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }
}
