package com.example.productcatalog.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    public static final long ROOT_CATEGORY_ID = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoryIdGenerator")
    @SequenceGenerator(name = "categoryIdGenerator", sequenceName = "category_id_seq", allocationSize = 5)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> subCategories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public void addSubCategory(Category subCategory) {
        subCategory.setParentCategory(this);
        subCategories.add(subCategory);
    }
}
