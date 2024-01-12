package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post rest Api

   @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost (@Valid @RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get all post REST API

    @GetMapping("/allPosts")
    public PostResponse getAllPosts(@RequestParam(value="pageNo",defaultValue = "0",required = false) int pageNo,
                                    @RequestParam(value="pageSize",defaultValue ="5",required = false) int pazeSize,
                                    @RequestParam(value="sortBy", defaultValue = "id", required = false) String sortBy,
                                    @RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir
                                     ){
         return postService.getAllPost(pageNo, pazeSize,sortBy,sortDir);
    }

    //get post by id REST API

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id)
    {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    //Update post by id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name="id") long id){

     PostDto postResponse= postService.updatePost(postDto,id);

        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    //delete post REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id){
          postService.deletePostById(id);
          return new ResponseEntity<>("Post Deleted Succesfully",HttpStatus.OK);
    }


}
