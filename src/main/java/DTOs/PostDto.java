package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Post;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String id;
    @NotNull
    private LocalDate pubDate;
    private String body;
    private String title;
    private String authorId;

    public static PostDto packDto(Post post){
        PostDto postdto = new PostDto();
        postdto.setId(post.getAuthorId());
        postdto.setAuthorId(post.getAuthorId());
        postdto.setBody(post.getBody());
        postdto.setTitle(post.getTitle());
        postdto.setPubDate(post.getPubDate());

        return postdto;
    }

    public static Post unpackDto(PostDto postDto){
        Post post = new Post();
        post.setId(postDto.getAuthorId());
        post.setAuthorId(postDto.getAuthorId());
        post.setPubDate(postDto.getPubDate());
        post.setTitle(postDto.getTitle());
        post.setBody(postDto.getBody());

        return post;
    }
}
