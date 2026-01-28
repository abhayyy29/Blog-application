package com.abhay.blog.blogapplication.repositeries;


import org.springframework.data.jpa.repository.JpaRepository;

import com.abhay.blog.blogapplication.entities.Category;

public interface CategoryRepo  extends JpaRepository<Category,Integer>{

    
}
