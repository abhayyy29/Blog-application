package com.abhay.blog.blogapplication.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhay.blog.blogapplication.entities.Comment;
import com.abhay.blog.blogapplication.entities.Post;
import com.abhay.blog.blogapplication.entities.User;
import com.abhay.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.abhay.blog.blogapplication.payloads.CommentDto;
import com.abhay.blog.blogapplication.repositeries.CommentRepo;
import com.abhay.blog.blogapplication.repositeries.PostRepo;
import com.abhay.blog.blogapplication.repositeries.UserRepo;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
      User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserID", userId));
    Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostID", postId));
  Comment comment =  this.modelMapper.map(commentDto, Comment.class);
    comment.setPost(post);
    comment.setUser(user);
    Comment savedComment = this.commentRepo.save(comment);
    return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
      Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "CommentID", commentId));
      this.commentRepo.delete(com);
    }

    @Override
    public List<CommentDto> getCommentsByPost(Integer postId) {
      Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", "postId"));
      List<Comment> comments = this.commentRepo.findByPost(post);
      return comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }

}
