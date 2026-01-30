package com.abhay.blog.blogapplication.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private int categoryId;

    @NotBlank
    @Size(min = 4, message = "Title must be min of 4 char")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, message = "Desciption must be min of 4 char")
    private String categoryDescription;

}
