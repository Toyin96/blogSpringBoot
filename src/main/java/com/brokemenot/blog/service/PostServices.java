package com.brokemenot.blog.service;

import com.brokemenot.blog.DTOs.PostDto;
import com.brokemenot.blog.Exception.PostException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostServices {
    void createPost(PostDto postDTO);
    List<PostDto> getAllPosts() throws PostException;
    PostDto getPostById(String id) throws PostException;
    String deletePostById(String id) throws PostException;
    String deletePostByTitle(String title) throws PostException;
    String deleteAllPostsByAuthorId(String authorId) throws PostException;
    PostDto updatePostById(String id, PostDto postDto) throws PostException;

}
