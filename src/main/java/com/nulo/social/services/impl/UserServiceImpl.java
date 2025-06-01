package com.nulo.social.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nulo.social.model.usuario.RecSaveUser;
import com.nulo.social.model.usuario.UserEntity;
import com.nulo.social.repository.UserRepository;
import com.nulo.social.services.UserService;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	@Transactional
	public UserEntity save(@Valid RecSaveUser recSaveUser) {
		UserEntity user = RecSaveUser.toEntity(recSaveUser);
		return repository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	// TODO: Adicionar paginação e ordenação conforme doc
	public List<UserEntity> list() {
		return repository.findAll();
	}

}
