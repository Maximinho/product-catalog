package com.example.productcatalog.mapper.impl;

import com.example.productcatalog.mapper.ProductMapper;
import com.example.productcatalog.model.Category;
import com.example.productcatalog.model.Product;
import com.example.productcatalog.web.dto.ProductDto;
import com.example.productcatalog.web.request.ProductRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductRequest productRequest) {
        Category category = null;
        if (productRequest.getCategoryId() != null) {
            category = new Category();
            category.setId(productRequest.getCategoryId());
        }

        Product product = new Product();
        product.setName(productRequest.getName());
        if (category != null) {
            product.setCategory(category);
        }

        return product;
    }

    @Override
    public ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getCategory().getId());
    }
}
