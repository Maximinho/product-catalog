package com.example.productcatalog.repository;

import com.example.productcatalog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "with recursive category_tree as (\n" +
            "    select * from category where parent_id = :categoryId\n" +
            "    union all\n" +
            "    select c.* from category c join category_tree ct on c.parent_id = ct.id\n" +
            ")\n" +
            "select * from category_tree",
            countQuery = "with recursive category_tree as (\n" +
                    "    select id from category where parent_id = :categoryId\n" +
                    "    union all\n" +
                    "    select c.id from category c join category_tree ct on c.parent_id = ct.id\n" +
                    ")\n" +
                    "select count(*) from category_tree",
            nativeQuery = true)
    Page<Category> findCategoriesByCategoryId(@Param("categoryId") long categoryId, Pageable pageable);
}
