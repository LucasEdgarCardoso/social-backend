package com.nulo.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nulo.social.model.dto.PaginatedResponse;
import com.nulo.social.model.dto.RecPageRequest;
import com.nulo.social.model.post.PostEntity;
import com.nulo.social.model.post.RecPostOutput;
import com.nulo.social.model.post.RecSavePost;
import com.nulo.social.model.post.RecUpdatePost;
import com.nulo.social.services.PostService;

import jakarta.validation.Valid;


/**
 * Controlador REST para operações com usuários.
 */
@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping
	public ResponseEntity<RecPostOutput> save(@RequestBody @Valid RecSavePost recSavePost) {
		PostEntity post = postService.save(recSavePost);
		return ResponseEntity.ok().body(new RecPostOutput(post));
	}
	
	@PutMapping
	public ResponseEntity<RecPostOutput> update(@RequestBody @Valid RecUpdatePost recUpdatePost) {
		PostEntity post = postService.update(recUpdatePost);
		return ResponseEntity.ok().body(new RecPostOutput(post));
	}

	@PostMapping("/list")
	public ResponseEntity<PaginatedResponse<RecPostOutput>> list(@RequestBody RecPageRequest pageRequest) {
		Page<PostEntity> posts = postService.list(pageRequest.toPageRequest());
		return ResponseEntity.ok().body(new PaginatedResponse<>(posts, post -> new RecPostOutput(post)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable String id) {
		PostEntity post = postService.getOne(id);
		return ResponseEntity.ok().body(new RecPostOutput(post));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		postService.executeLogicalDelete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Object> like(@PathVariable String id) {
		postService.like(id);
		return ResponseEntity.ok().build();
	}
	
}
