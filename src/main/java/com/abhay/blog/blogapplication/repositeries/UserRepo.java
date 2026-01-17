package com.abhay.blog.blogapplication.repositeries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhay.blog.blogapplication.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
