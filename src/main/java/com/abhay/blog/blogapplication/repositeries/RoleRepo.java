package com.abhay.blog.blogapplication.repositeries;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abhay.blog.blogapplication.entities.Role;


public interface RoleRepo extends JpaRepository<Role,Integer>{
    boolean existsByName(String name);
    Optional<Role> findByName(String name);
}
