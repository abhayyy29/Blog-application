package com.abhay.blog.blogapplication.services;

import java.util.Date;
import java.util.List;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhay.blog.blogapplication.entities.Category;
import com.abhay.blog.blogapplication.entities.Post;
import com.abhay.blog.blogapplication.entities.User;
import com.abhay.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.abhay.blog.blogapplication.payloads.PostDto;
import com.abhay.blog.blogapplication.repositeries.CategoryRepo;
import com.abhay.blog.blogapplication.repositeries.PostRepo;
import com.abhay.blog.blogapplication.repositeries.UserRepo;


@Service
public class PostServiceImpl  implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;
   
    @Override

    public PostDto createPost(PostDto postDto , Integer userId, Integer categoryId) {
        
        User user =  this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserID", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "Category_id", categoryId));

        Post post =  this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
       Post createdPost = this.postRepo.save(post);
       return this.modelMapper.map(createdPost, PostDto.class);
    

    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePost'");
    }

    @Override
    public void deletePost(Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }

    @Override
    public List<Post> getAllPost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPost'");
    }

    @Override
    public Post gePostById(Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gePostById'");
    }

    @Override
    public List<Post> getPostsByCategory(Integer categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostsByCategory'");
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostsByUser'");
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPosts'");
    }

}
