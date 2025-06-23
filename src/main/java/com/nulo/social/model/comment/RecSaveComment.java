package com.nulo.social.model.comment;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotNull;

public record RecSaveComment(
		@NotNull(message = "body cannot be null") String body,
		@NotNull(message = "postId cannot be null") String postId,
		@NotNull(message = "body cannot be null") String authorId
) {
	
	public static CommentEntity toEntity(RecSaveComment recPost) {
        var comment = new CommentEntity();
        comment.setBody(recPost.body);
        comment.setPost(new ObjectId(recPost.postId));
        comment.setAuthor(new ObjectId(recPost.authorId));
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }
	
}
