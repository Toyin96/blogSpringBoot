package com.brokemenot.blog.controllers;

import com.brokemenot.blog.DTOs.ApiResponse;
import com.brokemenot.blog.DTOs.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.brokemenot.blog.service.PostServices;
import com.brokemenot.blog.Exception.PostException;

import java.util.List;

@Slf4j
@RestController
//@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostServices postServices;

    @GetMapping("/")
    public String home(){
        return "Hello Core";
    }

    @PostMapping("/new")
    public ResponseEntity<?>createPost(@RequestBody PostDto postDto){
        log.info("postDto : {}", postDto);
        postServices.createPost(postDto);
        return new ResponseEntity<>(new ApiResponse(true, "post created successfully"), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getPostById(@RequestBody @PathVariable String id) throws PostException {
        log.info("id : {}", id);
        try{
            PostDto postDto = postServices.getPostById(id);
            return new ResponseEntity<>(PostDto.unpackDto(postDto), HttpStatus.OK);
        } catch (PostException postException) {
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updatePostById(@RequestBody String id, PostDto updatedPostDto){
        log.info("id, undatedPostDto : {}{}", id, updatedPostDto);
        try{
            PostDto updatePost = postServices.updatePostById(id, updatedPostDto);
            return new ResponseEntity<>(updatePost, HttpStatus.OK);

        } catch (PostException postException) {
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePostById(@RequestBody @PathVariable String id){
        log.info("id : {}", id);
        try{
            String message = postServices.deletePostById(id);
            return new ResponseEntity<>(new ApiResponse(true, message), HttpStatus.OK);
        } catch (PostException postException) {
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{title}")
    public ResponseEntity<?> deletePostByTitle(@RequestBody @PathVariable String title){
        log.info("title : {}", title);
        try{
            String message = postServices.deletePostByTitle(title);
            return new ResponseEntity<>(new ApiResponse(true, message), HttpStatus.OK);
        } catch (PostException postException) {
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{authorId}")
    public ResponseEntity<?> deleteAllPostsByAuthorId(@RequestBody @PathVariable String authorId) throws PostException {
        log.info("authorId : {}", authorId);
        try {
            String message = postServices.deleteAllPostsByAuthorId(authorId);
            return new ResponseEntity<>(new ApiResponse(true, message), HttpStatus.OK);
        }catch (PostException postException){
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPosts(){
        try{
            List<PostDto> allPosts = postServices.getAllPosts();
            return new ResponseEntity<>(new ApiResponse(true, allPosts.toString()), HttpStatus.OK);
        } catch (PostException postException) {
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}