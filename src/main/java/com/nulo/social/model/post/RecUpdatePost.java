package com.nulo.social.model.post;

import com.nulo.social.model.user.RecUpdateUser;
import com.nulo.social.model.user.UserEntity;
import com.nulo.social.services.PostService;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public record RecUpdatePost (
    @NotNull String id,
    String body,
    String[] tags
){

    public static PostEntity updateValues(PostEntity post, RecUpdatePost recPost) {
        Optional.ofNullable(recPost)
                .map(RecUpdatePost::body)
                .ifPresent(newBody -> post.setBody(newBody));
        Optional.ofNullable(recPost)
                .map(RecUpdatePost::tags)
                .ifPresent(newTags -> post.setTags(newTags));
        return post;
    }

}
