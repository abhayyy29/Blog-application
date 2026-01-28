package com.abhay.blog.blogapplication.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhay.blog.blogapplication.entities.Category;
import com.abhay.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.abhay.blog.blogapplication.payloads.CategoryDto;
import com.abhay.blog.blogapplication.repositeries.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto CategoryDto) {
       Category cat =  this.modelMapper.map(CategoryDto, Category.class);
       Category addedCat = this.categoryRepo.save(cat);
       return this.modelMapper.map(addedCat, CategoryDto.class);

        
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
       Category cat = this.categoryRepo.findById(categoryId)
       .orElseThrow(()-> new ResourceNotFoundException("Category", "category ID" , categoryId));
       cat.setCategoryTitle(categoryDto.getCategoryTitle());
       cat.setCategoryDescription(categoryDto.getCategoryDescription());
       Category updatedCat = this.categoryRepo.save(cat);

       return this.modelMapper.map(updatedCat, CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer categoryId) {
       Category cat = this.categoryRepo.findById(categoryId)
       .orElseThrow(()-> new ResourceNotFoundException("Category", "category ID" , categoryId));
       this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getSingleCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category ID", categoryId));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();
       List<CategoryDto> catDtos = categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
       return catDtos;
    }


}
