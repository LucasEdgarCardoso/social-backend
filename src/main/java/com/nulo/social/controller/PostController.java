package com.nulo.social.controller;

import com.nulo.social.model.dto.PaginatedResponse;
import com.nulo.social.model.post.*;
import com.nulo.social.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

	@GetMapping
	public ResponseEntity<PaginatedResponse<RecPostOutput>> list(@RequestBody @Valid RecListPost recListPost) {
		Page<PostEntity> posts = postService.list(recListPost.body(), recListPost.pageRequest().toPageRequest());
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
}
