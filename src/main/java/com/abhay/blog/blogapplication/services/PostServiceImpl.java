package com.abhay.blog.blogapplication.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public PostDto  updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
          Post updatedPost =  this.postRepo.save(post);
          return this.modelMapper.map(updatedPost, PostDto.class); 
    }

    @Override
    public void deletePost(Integer postId) {
       Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostID", postId));
         this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
      List<Post> allPosts = this.postRepo.findAll();
      List<PostDto> postDtos = allPosts.stream().map((post)-> this.modelMapper.map( post, PostDto.class)).collect(Collectors.toList());
      return postDtos;
    }

    @Override
    public PostDto gePostById(Integer postId) {
      Post singlePost = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostID", postId));
       return this.modelMapper.map(singlePost, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
       
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "CategoryId", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postDtos =    posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
}

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
      
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPosts'");
    }

}
