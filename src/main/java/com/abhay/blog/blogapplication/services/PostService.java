package com.abhay.blog.blogapplication.services;

import java.util.List;
import com.abhay.blog.blogapplication.payloads.PostDto;
import com.abhay.blog.blogapplication.payloads.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto , Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostDto gePostById(Integer postId);

    PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);

    PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize);

    List<PostDto> searchPosts(String keyword);
}
