package com.abhay.blog.blogapplication.services;

import java.util.List;

import com.abhay.blog.blogapplication.payloads.CategoryDto;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto CategoryDto);

    public CategoryDto updateCategory(CategoryDto CategoryDto, Integer categoryId);

    void  deleteCategory(Integer categoryId);

    public CategoryDto getSingleCategory(Integer categoryId);

    List<CategoryDto> getCategories();







}
