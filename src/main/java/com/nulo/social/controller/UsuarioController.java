package com.nulo.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//	@PostMapping
//	public ResponseEntity<RecUserOutput> save(@RequestBody @Valid RecSaveUser recSaveUser) {
//		UserEntity user = userService.save(recSaveUser);
//		return ResponseEntity.ok().body(RecUserOutput.toRec(user));
//	}
	
	@PutMapping
	public ResponseEntity<RecUserOutput> save(@RequestBody @Valid RecUpdateUser recUpdateUser) {
		UserEntity user = userService.update(recUpdateUser);
		return ResponseEntity.ok().body(RecUserOutput.toRec(user));
	}

	@GetMapping
	public ResponseEntity<List<RecUserOutput>> list() {
		List<UserEntity> users = userService.list();
		return ResponseEntity.ok().body(RecUserOutput.toRec(users));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		userService.deleteLogically(id);
		return ResponseEntity.noContent().build();
	}
}
