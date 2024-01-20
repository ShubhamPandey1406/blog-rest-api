package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(
        name="CRUD REST API for POST Resource"
)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post rest Api
    @Operation(
            summary = "Create Post Rest API",
            description="Create Post Rest API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description="HTTP Status 201 CREATED"
    )
   @SecurityRequirement(
           name="Bearer Authentication"
   )


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
    @SecurityRequirement(
            name="Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable(name="id") long id){

     PostDto postResponse= postService.updatePost(postDto,id);

        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    //delete post REST API
    @SecurityRequirement(
            name="Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id){
          postService.deletePostById(id);
          return new ResponseEntity<>("Post Deleted Succesfully",HttpStatus.OK);
    }

     //Build getpost by category rest API
@GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(value="id") Long categoryId)
    {
      List<PostDto> postDtos=postService.getPostsByCategory(categoryId);

      return ResponseEntity.ok(postDtos);
    }

}
