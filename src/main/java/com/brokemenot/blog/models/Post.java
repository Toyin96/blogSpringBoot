package com.brokemenot.blog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    private String id;
    private LocalDate pubDate = LocalDate.now();
    private String body;
    private String title;
    private String authorId;
}
