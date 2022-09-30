package com.mita.loan.repository;

import com.mita.loan.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
    @Query(value = """
            SELECT * FROM Categories AS cat
            WHERE cat.CategoryName = :categoryName
            """,nativeQuery = true)
    Categories getByName(@Param("categoryName") String categoryName);
}
