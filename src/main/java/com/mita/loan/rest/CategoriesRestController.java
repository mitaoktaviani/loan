package com.mita.loan.rest;

import com.mita.loan.dto.categories.UpsertCategories;
import com.mita.loan.entity.Categories;
import com.mita.loan.service.CategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @Operation(summary = "List of Category Loan Product")
    @GetMapping("/list")
    public ResponseEntity<Object> list(){

        List<Categories> categories = categoriesService.getAllCategories();

        if(categories == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category is Not Found");
        }else{
            return new ResponseEntity<>(categories,HttpStatus.OK);
        }
    }

    @Operation(summary = "Update Category Loan Product")
    @PutMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody UpsertCategories dto,
                                         BindingResult bindingResult){

        Categories categories = categoriesService.getCategory(dto.getId());
        if(categories == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Not Found");
        }else if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Validation Failed, Http Request is not validated.");
        }else{
            Categories categories1 = categoriesService.updateCategory(dto);

            return new ResponseEntity<>(categories1,HttpStatus.OK);
        }
    }

}
