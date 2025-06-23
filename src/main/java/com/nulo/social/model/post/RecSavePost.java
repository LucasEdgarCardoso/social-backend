package com.nulo.social.model.post;

import com.nulo.social.model.user.RecSaveUser;
import com.nulo.social.model.user.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record RecSavePost (
@NotNull(message = "Body cannot be null") String body,

String[] tags,

Data createdAt
){

    public static PostEntity toEntity(RecSavePost recPost) {
        var post = new PostEntity();
        post.setBody(recPost.body);
        post.setTags(recPost.tags);
        post.setCreatedAt(recPost.createdAt);
        return post;
    }
}
