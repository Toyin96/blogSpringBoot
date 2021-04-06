package service;

import DTOs.PostDto;
import Exception.PostException;
import models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostServices {
    void createPost(PostDto postDTO);
    List<Post> getAllPosts();
    PostDto getPostById(String id) throws PostException;
    void deletePostById(String id) throws PostException;
    void deletePostByTitle(String title) throws PostException;
    void deleteAllPostsByAuthorId(String authorId);
    Post updatePost(String id, PostDto postDto);
}
