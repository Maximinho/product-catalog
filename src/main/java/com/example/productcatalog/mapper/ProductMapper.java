package com.example.productcatalog.mapper;

import com.example.productcatalog.model.Product;
import com.example.productcatalog.web.dto.ProductDto;
import com.example.productcatalog.web.request.ProductRequest;

public interface ProductMapper {
    Product toEntity(ProductRequest productRequest);

    ProductDto toDto(Product product);
}
