package com.mita.loan.rest;

import com.mita.loan.dto.categories.UpsertCategories;
import com.mita.loan.service.CategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories")
public class CategoriesRestController {

    @Autowired
    private CategoriesService categoriesService;

    @Operation(summary = "Add new Category Loan Product")
    @PostMapping("/add")
    public ResponseEntity<Object> add(@Valid @RequestBody UpsertCategories dto,
                                      BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Validation Failed, Http Request is not validated.");
        }else{
            Integer responId = categoriesService.addCategories(dto);
            dto.setId(responId);

            return new ResponseEntity<>("Successful add new Category",HttpStatus.CREATED);
        }
    }
}
