package com.example.productcatalog.service.impl;

import com.example.productcatalog.exception.ResourceNotFoundException;
import com.example.productcatalog.model.Category;
import com.example.productcatalog.model.Product;
import com.example.productcatalog.repository.ProductRepository;
import com.example.productcatalog.service.CategoryService;
import com.example.productcatalog.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Transactional
    @Override
    public Product createProduct(Product product) {
        checkCategoryForExistence(product.getCategory().getId());

        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product updateProduct(long productId, Product product) {
        Product productFromRepository = findProductByIdFromRepository(productId);
        Category category = product.getCategory();

        checkCategoryForExistence(category.getId());

        productFromRepository.setCategory(category);
        productFromRepository.setName(product.getName());

        return productRepository.save(productFromRepository);
    }

    @Transactional(readOnly = true)
    @Override
    public Product findProductById(long productId) {
        return findProductByIdFromRepository(productId);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Product> findProductsByCategoryId(long categoryId, Pageable pageable) {
        return productRepository.findProductsByCategoryId(categoryId, pageable);
    }

    @Transactional
    @Override
    public void deleteProductById(long productId) {
        productRepository.delete(findProductByIdFromRepository(productId));
    }

    private Product findProductByIdFromRepository(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id=" + productId + " not found"));
    }

    private void checkCategoryForExistence(long categoryId) {
        if (!categoryService.existsCategoryById(categoryId)) {
            throw new ResourceNotFoundException("Category with id=" + categoryId + " not found");
        }
    }
}
