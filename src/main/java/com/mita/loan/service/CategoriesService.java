package com.mita.loan.service;

import com.mita.loan.dto.categories.UpsertCategories;
import com.mita.loan.entity.Categories;

import java.util.List;

public interface CategoriesService {
    Integer addCategories(UpsertCategories dto);

    List<Categories> getAllCategories();

    Categories updateCategory(UpsertCategories dto);

    Categories getCategory(int id);

}
