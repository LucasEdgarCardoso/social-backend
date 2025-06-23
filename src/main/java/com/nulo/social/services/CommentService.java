package com.nulo.social.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.nulo.social.model.comment.CommentEntity;
import com.nulo.social.model.comment.RecSaveComment;

import jakarta.validation.Valid;

public interface CommentService {

	CommentEntity save(@Valid RecSaveComment recSaveComment);

	Page<CommentEntity> list(PageRequest pageRequest);

	void delete(String id);

	void like(String id);

}
