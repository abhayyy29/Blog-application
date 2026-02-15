package com.abhay.blog.blogapplication.repositeries;


import org.springframework.data.jpa.repository.JpaRepository;
import com.abhay.blog.blogapplication.entities.Role;


public interface RoleRepo extends JpaRepository<Role,Integer>{

    
}
