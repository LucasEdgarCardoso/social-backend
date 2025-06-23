package com.nulo.social.services;

import com.nulo.social.model.post.PostEntity;
import com.nulo.social.model.post.RecSavePost;
import com.nulo.social.model.post.RecUpdatePost;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostEntity save(@Valid RecSavePost recSavePost);

    PostEntity update(@Valid RecUpdatePost recUpdatePost);

    void executeLogicalDelete(@NotNull String postId);

    Page<PostEntity> list(String bodyFilter, Pageable pageRequest);

    PostEntity getOne(String id);
}
