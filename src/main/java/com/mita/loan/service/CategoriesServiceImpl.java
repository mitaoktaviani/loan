package com.mita.loan.service;

import com.mita.loan.dto.categories.UpsertCategories;
import com.mita.loan.entity.Categories;
import com.mita.loan.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServiceImpl implements CategoriesService{

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Integer addCategories(UpsertCategories dto) {
        Categories categories = new Categories();
        categories.setCategoryName(dto.getCategoryName());

        categoriesRepository.save(categories);
        return categories.getId();
    }
}
