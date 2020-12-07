package com.example.productcatalog.web;

import com.example.productcatalog.mapper.ProductMapper;
import com.example.productcatalog.model.Product;
import com.example.productcatalog.service.ProductService;
import com.example.productcatalog.web.dto.PageResponse;
import com.example.productcatalog.web.dto.ProductDto;
import com.example.productcatalog.web.request.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping(ProductController.PRODUCTS_URL_PATH)
@RestController
public class ProductController {

    public static final String PRODUCTS_URL_PATH = "/api/products";

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductRequest productRequest,
                                                    UriComponentsBuilder uriComponentsBuilder) {
        Product product = productService.createProduct(productMapper.toEntity(productRequest));
        URI productUri = uriComponentsBuilder.path(PRODUCTS_URL_PATH + "/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(productUri).body(productMapper.toDto(product));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProductDto>> getProducts(@RequestParam(defaultValue = "0") long categoryId,
                                                                @RequestParam(defaultValue = "1") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        Page<ProductDto> productDtoPage = productService.findProductsByCategoryId(categoryId, PageRequest.of(page - 1, size))
                .map(productMapper::toDto);
        PageResponse<ProductDto> productDtoPageResponse = new PageResponse<>(
                productDtoPage.getContent(),
                page,
                size,
                productDtoPage.getTotalPages(),
                productDtoPage.getTotalElements()
        );

        return ResponseEntity.ok(productDtoPageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") long productId,
                                                    @Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productMapper.toDto(productService.updateProduct(productId, productMapper.toEntity(productRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") long productId) {
        return ResponseEntity.ok(productMapper.toDto(productService.findProductById(productId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long productId) {
        productService.deleteProductById(productId);

        return ResponseEntity.noContent().build();
    }
}
