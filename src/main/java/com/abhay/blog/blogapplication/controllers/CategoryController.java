package com.abhay.blog.blogapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhay.blog.blogapplication.payloads.ApiResponse;
import com.abhay.blog.blogapplication.payloads.CategoryDto;
import com.abhay.blog.blogapplication.services.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto cateogDto){
     CategoryDto createdCategory = this.categoryService.createCategory(cateogDto);
     return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    
    }

     @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryId, @PathVariable Integer catId){
     CategoryDto updatedCategory = this.categoryService.updateCategory(categoryId, catId);
     return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    
    }

     @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
     this.categoryService.deleteCategory(catId);
     return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Sucessfully!!", true), HttpStatus.OK);
    
    }

     @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer catId){
     CategoryDto categoryDto= this.categoryService.getSingleCategory(catId);
     return new ResponseEntity<CategoryDto>(categoryDto , HttpStatus.OK);
    }
 @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories(){
     List<CategoryDto> categories= this.categoryService.getCategories();
     return ResponseEntity.ok(categories);
    }


}
