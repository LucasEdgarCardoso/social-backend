package com.nulo.social.services.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nulo.social.exception.ServiceException;
import com.nulo.social.model.user.RecSaveUser;
import com.nulo.social.model.user.RecUpdateUser;
import com.nulo.social.model.user.UserEntity;
import com.nulo.social.repository.UserRepository;
import com.nulo.social.services.UserService;
import com.nulo.social.utils.MessageUtils;

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
			throw new ServiceException(MessageUtils.EMAIL_USED_BY_ANOTHER_USER, HttpStatus.BAD_REQUEST);
		}
		
		UserEntity user = RecSaveUser.toEntity(recSaveUser);
		return repository.save(user);
	}
	
	@Override
	@Transactional
	public UserEntity update(@Valid RecUpdateUser recUpdateUser) {
		var userId = new ObjectId(recUpdateUser.id());
		UserEntity savedUser = repository.findById(userId)
				.orElseThrow(() -> new ServiceException(MessageUtils.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
		UserEntity newUser = RecUpdateUser.updateValues(savedUser, recUpdateUser);
		
		repository.update(userId, newUser.getName(), newUser.getBio());
		return newUser;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserEntity> list(String nameFilter, Pageable pageRequest) {
		return repository.findByNamePageable(nameFilter, pageRequest);
	}
	
	@Override
	@Transactional
	public void executeLogicalDelete(@NotNull String userId) {
		if (ObjectId.isValid(userId)) {
			repository.deleteLogically(new ObjectId(userId));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserEntity getOneByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new ServiceException(MessageUtils.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
	}
	
    @Override
    @Transactional(readOnly = true)
    public UserEntity getOneById(String id) {
        return repository.findById(new ObjectId(id))
                .orElseThrow(() -> new ServiceException(MessageUtils.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

}
