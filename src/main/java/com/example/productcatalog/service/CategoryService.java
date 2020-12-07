package com.example.productcatalog.service;

import com.example.productcatalog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Category createCategory(Category category);

    Category updateCategory(long categoryId, Category category);

    Category findCategoryById(long categoryId);

    Page<Category> findCategoriesForCategoryById(long categoryId, Pageable pageable);

    boolean existsCategoryById(long categoryId);
}
