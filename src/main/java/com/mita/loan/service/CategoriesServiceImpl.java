package com.mita.loan.service;

import com.mita.loan.dto.categories.UpsertCategories;
import com.mita.loan.entity.Categories;
import com.mita.loan.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Categories> getAllCategories() {
        List<Categories> categories = categoriesRepository.findAll();
        return categories;
    }

    @Override
    public Categories updateCategory(UpsertCategories dto) {
        Optional<Categories> theCategory = categoriesRepository.findById(dto.getId());
        Categories categories = null;
        if(theCategory.isPresent()){
            categories= theCategory.get();}

        if(dto.getCategoryName()!= null){
            categories.setCategoryName(dto.getCategoryName());}

        categoriesRepository.save(categories);

        return categories;
    }

    @Override
    public Categories getCategory(int id) {
        Optional<Categories> theCategory = categoriesRepository.findById(id);
        Categories categories = null;
        if(theCategory.isPresent()){
            categories= theCategory.get();}

        return categories;
    }



}
