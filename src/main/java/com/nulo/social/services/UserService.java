package com.nulo.social.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nulo.social.model.user.RecSaveUser;
import com.nulo.social.model.user.RecUpdateUser;
import com.nulo.social.model.user.UserEntity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UserService {

	UserEntity save(@Valid RecSaveUser recSaveUser);

	UserEntity update(@Valid RecUpdateUser recUpdateUser);

	void executeLogicalDelete(@NotNull String userId);

	Page<UserEntity> list(String nameFilter, Pageable pageRequest);

	UserEntity getOneByEmail(String email);
	
}
