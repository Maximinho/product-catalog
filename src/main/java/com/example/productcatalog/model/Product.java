package com.example.productcatalog.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdGenerator")
    @SequenceGenerator(name = "productIdGenerator", sequenceName = "product_id_seq", allocationSize = 5)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
