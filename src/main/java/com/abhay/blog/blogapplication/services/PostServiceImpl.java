package com.abhay.blog.blogapplication.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.abhay.blog.blogapplication.entities.Category;
import com.abhay.blog.blogapplication.entities.Post;
import com.abhay.blog.blogapplication.entities.User;
import com.abhay.blog.blogapplication.exceptions.ResourceNotFoundException;
import com.abhay.blog.blogapplication.payloads.PostDto;
import com.abhay.blog.blogapplication.payloads.PostResponse;
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
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
           Pageable p = PageRequest.of(pageNumber, pageSize);

      Page<Post> pagePost = this.postRepo.findAll(p);
      List<Post> allPosts = pagePost.getContent();
      List<PostDto> postDtos = allPosts.stream().map((post)-> this.modelMapper.map( post, PostDto.class)).collect(Collectors.toList());
      PostResponse postResponse = new PostResponse();
      postResponse.setContent(postDtos);
      postResponse.setPageNumber(pagePost.getNumber());
      postResponse.setPageSize(pagePost.getSize());
      postResponse.setTotalElements(pagePost.getTotalElements());
      postResponse.setTotalPages(pagePost.getTotalPages());
      postResponse.setLastPage(pagePost.isLast());
      return postResponse;
    }

    @Override
    public PostDto gePostById(Integer postId) {
      Post singlePost = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostID", postId));
       return this.modelMapper.map(singlePost, PostDto.class);
    }

    @Override
    public PostResponse getPostsByCategory(Integer categoryId , Integer pageNumber, Integer pageSize) {
       
        Pageable p = PageRequest.of(pageNumber, pageSize);
       Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "CategoryId", categoryId));
        Page<Post> pagePost = this.postRepo.findByCategory(category, p); 
       List<Post> posts = pagePost.getContent();       
        List<PostDto> postDtos =    posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
      postResponse.setPageSize(pagePost.getSize());
      postResponse.setTotalElements(pagePost.getTotalElements());
      postResponse.setTotalPages(pagePost.getTotalPages());
      postResponse.setLastPage(pagePost.isLast());
      return postResponse;
    }
    


    @Override
    public PostResponse getPostsByUser(Integer userId , Integer pageNumber, Integer pageSize) {
      
        Pageable p = PageRequest.of(pageNumber, pageSize);
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
        Page<Post> pagePost = this.postRepo.findByUser(user,p);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
         postResponse.setPageNumber(pagePost.getNumber());
      postResponse.setPageSize(pagePost.getSize());
      postResponse.setTotalElements(pagePost.getTotalElements());
      postResponse.setTotalPages(pagePost.getTotalPages());
      postResponse.setLastPage(pagePost.isLast());
      return postResponse;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPosts'");
    }

}
