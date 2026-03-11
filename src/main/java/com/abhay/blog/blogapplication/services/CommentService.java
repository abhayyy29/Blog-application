package com.abhay.blog.blogapplication.services;

import java.util.List;

import com.abhay.blog.blogapplication.payloads.CommentDto;

public interface CommentService {

    List<CommentDto> getCommentsByPost(Integer postId);
    CommentDto createComment(CommentDto CommentDto, Integer postId, Integer userId);
    void deleteComment(Integer commentId);
}
