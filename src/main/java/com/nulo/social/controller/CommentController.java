package com.nulo.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nulo.social.model.comment.CommentEntity;
import com.nulo.social.model.comment.RecCommentOutput;
import com.nulo.social.model.comment.RecSaveComment;
import com.nulo.social.model.dto.PaginatedResponse;
import com.nulo.social.model.dto.RecPageRequest;
import com.nulo.social.services.CommentService;

import jakarta.validation.Valid;

/**
 * Controlador REST para operações com comentários.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping
	public ResponseEntity<RecCommentOutput> save(@RequestBody @Valid RecSaveComment recSaveComment) {
		CommentEntity Comment = commentService.save(recSaveComment);
		return ResponseEntity.ok().body(new RecCommentOutput(Comment));
	}
	
	@GetMapping
	public ResponseEntity<PaginatedResponse<RecCommentOutput>> list(@RequestBody RecPageRequest pageRequest) {
		Page<CommentEntity> posts = commentService.list(pageRequest.toPageRequest());
		return ResponseEntity.ok().body(new PaginatedResponse<>(posts, post -> new RecCommentOutput(post)));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		commentService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Object> like(@PathVariable String id) {
		commentService.like(id);
		return ResponseEntity.ok().build();
	}
	
}
