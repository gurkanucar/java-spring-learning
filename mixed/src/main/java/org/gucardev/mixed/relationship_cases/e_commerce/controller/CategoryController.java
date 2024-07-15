package org.gucardev.mixed.relationship_cases.e_commerce.controller;

import org.gucardev.mixed.relationship_cases.e_commerce.dto.CategoryDTO;
import org.gucardev.mixed.relationship_cases.e_commerce.dto.ProductDTO;
import org.gucardev.mixed.relationship_cases.e_commerce.entitiy.Category;
import org.gucardev.mixed.relationship_cases.e_commerce.entitiy.Product;
import org.gucardev.mixed.relationship_cases.e_commerce.mapper.CategoryMapper;
import org.gucardev.mixed.relationship_cases.e_commerce.mapper.ProductMapper;
import org.gucardev.mixed.relationship_cases.e_commerce.repo.CategoryRepository;
import org.gucardev.mixed.relationship_cases.e_commerce.repo.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository categoryService;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public CategoryController(CategoryRepository categoryService, CategoryMapper categoryMapper, ProductMapper productMapper, ProductRepository productRepository) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> categoryDTOs = categories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryDTOs);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@PathVariable Long id) {
        List<Product> products = productRepository.findByCategories_Id(id);
        List<ProductDTO> productDTOs = products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping("/{id}/hierarchy")
    public ResponseEntity<List<CategoryDTO>> getCategoryHierarchy(@PathVariable Long id) {
        List<Category> hierarchy = findCategoryHierarchy(id);
        List<CategoryDTO> hierarchyDTOs = hierarchy.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(hierarchyDTOs);
    }

    public List<Category> findCategoryHierarchy(Long categoryId) {
        List<Category> hierarchy = new ArrayList<>();
        Category category = categoryService.findById(categoryId).orElse(null);
        while (category != null) {
            hierarchy.add(0, category); // Add to the beginning of the list to reverse the order
            category = category.getParent();
        }
        return hierarchy;
    }
}
