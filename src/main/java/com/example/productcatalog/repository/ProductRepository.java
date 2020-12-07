package com.example.productcatalog.repository;

import com.example.productcatalog.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "with recursive category_tree as (\n" +
            "    select id from category where id = :categoryId\n" +
            "    union all\n" +
            "    select c.id from category c join category_tree ct on c.parent_id = ct.id\n" +
            ")\n" +
            "select * from product p join category_tree ct on p.category_id = ct.id",
            countQuery = "with recursive category_tree as (\n" +
                    "    select id from category where id = :categoryId\n" +
                    "    union all\n" +
                    "    select c.id from category c join category_tree ct on c.parent_id = ct.id\n" +
                    ")\n" +
                    "select count(*) from product p join category_tree ct on p.category_id = ct.id",
            nativeQuery = true)
    Page<Product> findProductsByCategoryId(@Param("categoryId") long categoryId, Pageable pageable);
}
