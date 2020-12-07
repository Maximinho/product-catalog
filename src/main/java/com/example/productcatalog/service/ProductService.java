package com.example.productcatalog.service;

import com.example.productcatalog.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product createProduct(Product product);

    Product updateProduct(long productId, Product product);

    Product findProductById(long productId);

    Page<Product> findProductsByCategoryId(long categoryId, Pageable pageable);

    void deleteProductById(long productId);
}
