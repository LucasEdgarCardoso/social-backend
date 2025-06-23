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
import com.nulo.social.model.user.RecListUser;
import com.nulo.social.model.user.RecSaveUser;
import com.nulo.social.model.user.RecUpdateUser;
import com.nulo.social.model.user.RecUserOutput;
import com.nulo.social.model.user.UserEntity;
import com.nulo.social.services.UserService;

import jakarta.validation.Valid;


/**
 * Controlador REST para operações com usuários.
 */
@RestController
@RequestMapping("/user")
public class UsuarioController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<RecUserOutput> save(@RequestBody @Valid RecSaveUser recSaveUser) {
		UserEntity user = userService.save(recSaveUser);
		return ResponseEntity.ok().body(new RecUserOutput(user));
	}
	
	@PutMapping
	public ResponseEntity<RecUserOutput> update(@RequestBody @Valid RecUpdateUser recUpdateUser) {
		UserEntity user = userService.update(recUpdateUser);
		return ResponseEntity.ok().body(new RecUserOutput(user));
	}

	@GetMapping
	public ResponseEntity<PaginatedResponse<RecUserOutput>> list(@RequestBody @Valid RecListUser recListUser) {
		Page<UserEntity> users = userService.list(recListUser.name(), recListUser.pageRequest().toPageRequest());
		return ResponseEntity.ok().body(new PaginatedResponse<>(users, user -> new RecUserOutput(user)));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable String id) {
		UserEntity user = userService.getOne(id);
		return ResponseEntity.ok().body(new RecUserOutput(user));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		userService.executeLogicalDelete(id);
		return ResponseEntity.noContent().build();
	}
}
