package com.nulo.social.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nulo.social.model.post.PostEntity;
import com.nulo.social.model.post.RecSavePost;
import com.nulo.social.model.post.RecUpdatePost;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface PostService {

    PostEntity save(@Valid RecSavePost recSavePost);

    PostEntity update(@Valid RecUpdatePost recUpdatePost);

    void executeLogicalDelete(@NotNull String postId);

    PostEntity getOne(String id);

	Page<PostEntity> list(Pageable pageRequest);

	void like(String id);
}
