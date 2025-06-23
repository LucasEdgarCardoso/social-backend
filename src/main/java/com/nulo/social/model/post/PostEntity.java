package com.nulo.social.model.post;

import com.mongodb.lang.NonNull;
import com.nulo.social.model.comment.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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

    private Data createdAt;

    private int likes;

    private List<CommentEntity> comments;

    private Boolean deleted = false;
}
