package com.nulo.social.model.post;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "post")
public class PostEntity {

    @Id
    private ObjectId id;

    @NonNull
    private ObjectId author;

    @NonNull
    private String body;

    private String[] tags;

    private LocalDateTime createdAt;

    private int likes;

//    private List<CommentEntity> comments;

    private Boolean deleted = false;
}
