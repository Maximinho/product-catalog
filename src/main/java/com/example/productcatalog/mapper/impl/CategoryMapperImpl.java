package com.example.productcatalog.mapper.impl;

import com.example.productcatalog.mapper.CategoryMapper;
import com.example.productcatalog.model.Category;
import com.example.productcatalog.web.dto.CategoryDto;
import com.example.productcatalog.web.request.CategoryRequest;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryRequest categoryRequest) {
        Category parentCategory = new Category();
        parentCategory.setId(categoryRequest.getParentCategoryId());

        Category category = new Category();
        category.setParentCategory(parentCategory);
        parentCategory.addSubCategory(category);
        category.setName(categoryRequest.getName());

        return category;
    }

    @Override
    public CategoryDto toDto(Category category) {
        return new CategoryDto(category.getId(), category.getName(), category.getParentCategory().getId());
    }
}
