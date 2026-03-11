package com.abhay.blog.blogapplication.repositeries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhay.blog.blogapplication.entities.Comment;
import com.abhay.blog.blogapplication.entities.Post;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

    List<Comment> findByPost(Post post);
}
