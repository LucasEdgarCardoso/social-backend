package com.nulo.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nulo.social.model.usuario.RecSaveUser;
import com.nulo.social.model.usuario.RecUserOutput;
import com.nulo.social.model.usuario.UserEntity;
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

	@GetMapping
	public ResponseEntity<List<RecUserOutput>> listUsers() {
		List<UserEntity> users = userService.list();
		return ResponseEntity.ok().body(RecUserOutput.toRec(users));
	}
	
	@PostMapping
	public ResponseEntity<RecUserOutput> saveUser(@RequestBody @Valid RecSaveUser recSaveUser) {
		UserEntity user = userService.save(recSaveUser);
		return ResponseEntity.ok().body(RecUserOutput.toRec(user));
	}
}
