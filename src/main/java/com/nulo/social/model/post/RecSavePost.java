package com.nulo.social.model.post;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotNull;

public record RecSavePost (
		@NotNull(message = "body cannot be null") String body,
		@NotNull(message = "authorId cannot be null") String authorId,
		String[] tags
){

    public static PostEntity toEntity(RecSavePost recPost) {
        var post = new PostEntity();
        post.setBody(recPost.body);
        post.setTags(recPost.tags);
        post.setAuthor(new ObjectId(recPost.authorId));
        post.setCreatedAt(LocalDateTime.now());
        return post;
    }
}
