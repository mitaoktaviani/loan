package com.mita.loan.dto.categories;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter @Setter @ToString
public class UpsertCategories {
    private int id;
    @NotBlank(message = "Category Name is requires")
    private String categoryName;
}
