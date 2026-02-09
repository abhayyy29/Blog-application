package com.abhay.blog.blogapplication.services;

import com.abhay.blog.blogapplication.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto CommentDto, Integer postId, Integer userId);
    void deleteComment(Integer commentId);
}
