package com.abhay.blog.blogapplication.services;

import java.util.List;

import com.abhay.blog.blogapplication.payloads.UserDto;

public interface UserService {

   public UserDto createUser(UserDto user);
   public UserDto updateUser(UserDto user, Integer userId);
   public UserDto getUserById(Integer userId);
   List<UserDto> getAllUsers();
   void deleteUser(Integer userId); 
   }

