package com.mita.loan.service;

import com.mita.loan.dto.categories.UpsertCategories;

public interface CategoriesService {
    Integer addCategories(UpsertCategories dto);
}
