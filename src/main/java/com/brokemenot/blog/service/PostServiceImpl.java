package com.brokemenot.blog.service;

import com.brokemenot.blog.DTOs.PostDto;
import com.brokemenot.blog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import com.brokemenot.blog.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.brokemenot.blog.Exception.PostException;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostServices{
    @Autowired
    PostRepository postRepository;
    @Override
    public void createPost(PostDto postDTO) {
        createNewPost(postDTO);
    }

    private void createNewPost(PostDto postDTO){
        Post post = PostDto.unpackDto(postDTO);
        postRepository.save(post);
    }

    @Override
    public List<PostDto> getAllPosts() throws PostException {
        List<PostDto> allPostDtos = new ArrayList<>();
        for(Post post: fetchAllPosts()){
            allPostDtos.add(PostDto.packDto(post));
        }
        return allPostDtos;
    }

    private List<Post> fetchAllPosts() throws PostException {

        if (postRepository.findAll().isEmpty()){
            throw new PostException("No posts found in the database");
        }
        return postRepository.findAll();
    }

    @Override
    public PostDto getPostById(String id) throws PostException {
        Post post = getExistingPostById(id);
        return PostDto.packDto(post);
    }

    private Post getExistingPostById(String id) throws PostException {
        Optional<Post> post = postRepository.findPostById(id);
        if (post.isPresent()){
           return post.get();
        }else{
            throw new PostException("No post found by that ID");
        }
    }

    @Override
    public String deletePostById(String id) throws PostException {
        Optional<Post> post = postRepository.findPostById(id);
        if (post.isPresent()) {
            deletePostByPost(post.get());
            return "Post deleted successfully";
        }else{
            throw new PostException("No post found with that ID in the database.");
        }
    }

    private void deletePostByPost(Post post) throws PostException {
        postRepository.delete(post);
    }

    @Override
    public String deletePostByTitle(String title) throws PostException {
        Optional<Post> post = postRepository.findPostByTitle(title.toLowerCase());
        if (post.isPresent()){
            deletePostByPost(post.get());
            return "Post has been deleted successfully";
        }else{
            throw new PostException("No post found with that title in the database.");
        }
    }


    @Override
    public String deleteAllPostsByAuthorId(String authorId) throws PostException {
        return findAllPostByAuthorIdAndDelete(authorId);
    }

    private String findAllPostByAuthorIdAndDelete(String authorId) throws PostException {
        List<Post> posts = postRepository.findAllPostsByAuthorId(authorId);
        if (posts.isEmpty()){
            throw new PostException("No posts associated with the author id");
        }else{
            postRepository.deleteAll(posts);
            return "All articles by author has been deleted successfully";
        }
    }

    @Override
    public PostDto updatePostById(String id, PostDto postDtoToUpdate) throws PostException {
        PostDto existingPostDto = getPostById(id);
        if (!existingPostDto.getBody().equals(postDtoToUpdate.getBody())){
            existingPostDto.setBody(postDtoToUpdate.getBody());
        }

        if (!existingPostDto.getTitle().equals(postDtoToUpdate.getTitle())){
            existingPostDto.setTitle(postDtoToUpdate.getTitle());
        }

        if(!existingPostDto.getPubDate().equals(postDtoToUpdate.getPubDate())){
            existingPostDto.setPubDate(postDtoToUpdate.getPubDate());
        }

        Post post = savePostToRepository(PostDto.unpackDto(existingPostDto));
        return PostDto.packDto(post);
    }

    private Post savePostToRepository(Post postToSave){
       return postRepository.save(postToSave);
    }
}
