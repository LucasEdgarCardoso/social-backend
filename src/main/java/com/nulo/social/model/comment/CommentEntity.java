package com.nulo.social.model.comment;

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
@Document(collection = "comment")
public class CommentEntity {

	@Id
	private ObjectId id;

	@NonNull
	private ObjectId author;
	
    @NonNull
    private String body;
    
    private LocalDateTime createdAt;
    
    private int likes;

	private ObjectId post;

}
