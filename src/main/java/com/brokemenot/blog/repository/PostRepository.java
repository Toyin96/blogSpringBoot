package com.brokemenot.blog.repository;

import com.brokemenot.blog.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    Optional<Post> findPostById(String id);
    Optional<Post> findPostByTitle(String title);
    List<Post> findAllPostsByAuthorId(String authorId);
}
