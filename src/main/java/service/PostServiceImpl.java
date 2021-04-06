package service;

import DTOs.PostDto;
import models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import repository.PostRepository;

import java.util.List;
import java.util.Optional;
import Exception.PostException;

import javax.swing.text.html.Option;
import javax.xml.stream.events.DTD;

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
    public List<Post> getAllPosts() {
        return null;
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
    public void deletePostById(String id) throws PostException {
        Optional<Post> post = postRepository.findPostById(id);
        if (post.isPresent()) {
            deletePostByPost(post.get());
        }
    }

    private void deletePostByPost(Post post) throws PostException {
        postRepository.delete(post);
    }

    @Override
    public void deletePostByTitle(String title) throws PostException {
        Optional<Post> post = postRepository.findPostByTitle(title);
        if (post.isPresent()){
            deletePostByPost(post.get());
        }
    }


    @Override
    public void deleteAllPostsByAuthorId(String authorId) {

    }

//    private List<DTD> findAllPostByAuthorId(String authorId) throws PostException {
//        List<Post> posts = postRepository.findAllPostsByAuthorId(authorId);
//        if (posts.isEmpty()){
//            throw new PostException("No posts associated with the author id");
//        }else{
//
//        }
//    }

    @Override
    public Post updatePost(String id, PostDto postDto) {
        return null;
    }
}
