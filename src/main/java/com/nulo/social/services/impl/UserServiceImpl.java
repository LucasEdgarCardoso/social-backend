package com.nulo.social.services.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nulo.social.exception.ServiceException;
import com.nulo.social.model.user.RecSaveUser;
import com.nulo.social.model.user.RecUpdateUser;
import com.nulo.social.model.user.UserEntity;
import com.nulo.social.repository.UserRepository;
import com.nulo.social.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	@Transactional
	public UserEntity save(@Valid RecSaveUser recSaveUser) {
		
		if(repository.findByEmail(recSaveUser.email()).isPresent()) {
			new ServiceException("Emails já pertence a outro usuário", HttpStatus.BAD_REQUEST);
		}
		
		UserEntity user = RecSaveUser.toEntity(recSaveUser);
		return repository.save(user);
	}
	
	@Override
	@Transactional
	public UserEntity update(@Valid RecUpdateUser recUpdateUser) {
		UserEntity savedUser = repository.findById(new ObjectId(recUpdateUser.id()))
				.orElseThrow(() -> new ServiceException("Usuário não encontrado", HttpStatus.NOT_FOUND));
		
		UserEntity newUser = RecUpdateUser.updateValues(savedUser, recUpdateUser);
		return repository.save(newUser);
	}

	@Override
	@Transactional(readOnly = true)
	// TODO: Adicionar paginação e ordenação conforme doc
	public List<UserEntity> list() {
		return repository.findAll();
	}
	
	@Override
	@Transactional
	public void deleteLogically(@NotNull String userId) {
		if (ObjectId.isValid(userId)) {
			// TODO: Alterar o update para native Query do mongo?
			repository.findById(new ObjectId(userId)).ifPresent(user -> {
				user.setDeleted(Boolean.TRUE);
				repository.save(user);
			});
		}
	}
	
}
