package com.example.productcatalog.service.impl;

import com.example.productcatalog.exception.IllegalParentCategoryException;
import com.example.productcatalog.exception.ResourceNotFoundException;
import com.example.productcatalog.model.Category;
import com.example.productcatalog.repository.CategoryRepository;
import com.example.productcatalog.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public Category createCategory(Category category) {
        checkParentCategoryForExistence(category.getParentCategory().getId());

        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Category updateCategory(long categoryId, Category category) {
        Category categoryFromRepository = findCategoryByIdFromRepository(categoryId);

        Category parentCategory = category.getParentCategory();
        checkParentCategoryForExistence(parentCategory.getId());
        if (categoryFromRepository.getId().equals(parentCategory.getId())) {
            throw new IllegalParentCategoryException("Category with id=" + parentCategory.getId() +
                    " is illegal parent for category with id=" + categoryFromRepository.getId());
        }

        categoryFromRepository.setParentCategory(parentCategory);
        categoryFromRepository.setName(category.getName());

        return categoryRepository.save(categoryFromRepository);
    }

    @Transactional(readOnly = true)
    @Override
    public Category findCategoryById(long categoryId) {
        return findCategoryByIdFromRepository(categoryId);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Category> findCategoriesForCategoryById(long categoryId, Pageable pageable) {
        return categoryRepository.findCategoriesByCategoryId(categoryId, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsCategoryById(long categoryId) {
        return Category.ROOT_CATEGORY_ID != categoryId && categoryRepository.existsById(categoryId);
    }

    private Category findCategoryByIdFromRepository(long categoryId) {
        if (Category.ROOT_CATEGORY_ID == categoryId) {
            throw new ResourceNotFoundException("Category with id=" + categoryId + " not found");
        }

        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id=" + categoryId + " not found"));
    }

    private void checkParentCategoryForExistence(long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Parent category with id=" + categoryId + " not found");
        }
    }
}
