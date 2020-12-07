package com.example.productcatalog.web;

import com.example.productcatalog.mapper.CategoryMapper;
import com.example.productcatalog.model.Category;
import com.example.productcatalog.service.CategoryService;
import com.example.productcatalog.web.dto.CategoryDto;
import com.example.productcatalog.web.dto.PageResponse;
import com.example.productcatalog.web.request.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping(CategoryController.CATEGORIES_URL_PATH)
@RestController
public class CategoryController {

    public static final String CATEGORIES_URL_PATH = "/api/categories";

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryRequest categoryRequest,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        Category category = categoryService.createCategory(categoryMapper.toEntity(categoryRequest));
        URI categoryUri = uriComponentsBuilder.path(CATEGORIES_URL_PATH + "/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(categoryUri).body(categoryMapper.toDto(category));
    }

    @GetMapping
    public ResponseEntity<PageResponse<CategoryDto>> getCategories(@RequestParam(defaultValue = "0") long parentCategoryId,
                                                                   @RequestParam(defaultValue = "1") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Page<CategoryDto> categoryDtoPage = categoryService.findCategoriesForCategoryById(parentCategoryId, PageRequest.of(page - 1, size))
                .map(categoryMapper::toDto);
        PageResponse<CategoryDto> categoryDtoPageResponse = new PageResponse<>(
                categoryDtoPage.getContent(),
                page,
                size,
                categoryDtoPage.getTotalPages(),
                categoryDtoPage.getTotalElements()
        );

        return ResponseEntity.ok(categoryDtoPageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") long categoryId,
                                                      @Valid @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryMapper.toDto(categoryService.updateCategory(categoryId, categoryMapper.toEntity(categoryRequest))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") long categoryId) {
        return ResponseEntity.ok(categoryMapper.toDto(categoryService.findCategoryById(categoryId)));
    }
}
