package com.abhay.blog.blogapplication.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhay.blog.blogapplication.entities.User;
import com.abhay.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.abhay.blog.blogapplication.repositeries.UserRepo;

@Service
public class CoustomUserDetailService implements UserDetailsService{


    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    // loading User from datbase by username

    User user =this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email", username));

    return user;

    }
}
