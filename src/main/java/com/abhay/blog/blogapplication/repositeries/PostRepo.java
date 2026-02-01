package com.abhay.blog.blogapplication.repositeries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhay.blog.blogapplication.entities.Category;
import com.abhay.blog.blogapplication.entities.Post;
import com.abhay.blog.blogapplication.entities.User;

public interface PostRepo  extends JpaRepository<Post,Integer>{

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

}
