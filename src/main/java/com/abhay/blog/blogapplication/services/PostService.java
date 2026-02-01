package com.abhay.blog.blogapplication.services;

import java.util.List;

import com.abhay.blog.blogapplication.entities.Post;
import com.abhay.blog.blogapplication.payloads.PostDto;

public interface PostService {

    PostDto createPost(PostDto postDto , Integer userId, Integer categoryId);

    Post updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<Post> getAllPost();

    Post gePostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<Post> searchPosts(String keyword);
}
