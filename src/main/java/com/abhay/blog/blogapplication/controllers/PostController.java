package com.abhay.blog.blogapplication.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.abhay.blog.blogapplication.payloads.ApiResponse;
import com.abhay.blog.blogapplication.payloads.PostDto;
import com.abhay.blog.blogapplication.payloads.PostResponse;
import com.abhay.blog.blogapplication.services.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
        @RequestBody PostDto postDto,
        @PathVariable Integer userId,
        @PathVariable Integer categoryId )
    {
        PostDto  createPost =this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")    
    public ResponseEntity<PostResponse> getPostByUser(
        @PathVariable Integer userId,
        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
    ){
    PostResponse posts = this.postService.getPostsByUser(userId,pageNumber,pageSize);
    return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")    
    public ResponseEntity<PostResponse> getPostByCategory(
        @PathVariable Integer categoryId,
        @RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "5",required = false) Integer pageSize
    ){
    PostResponse posts = this.postService.getPostsByCategory(categoryId,pageNumber,pageSize);
    return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
        @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
    PostResponse allPost =  this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
    return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSinglePost( @PathVariable Integer postId){
        PostDto postDto = this.postService.gePostById(postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post succesfully deleted!! ",true);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable Integer postId){
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostsByTitle(
    @PathVariable("keywords") String keywords
    ){
    List<PostDto> result = this.postService.searchPosts(keywords);
    return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }

}
