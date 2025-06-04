package com.nulo.social.services;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.nulo.social.model.user.RecSaveUser;
import com.nulo.social.model.user.RecUpdateUser;
import com.nulo.social.model.user.UserEntity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UserService {

	UserEntity save(@Valid RecSaveUser recSaveUser) throws BadRequestException;

	UserEntity update(@Valid RecUpdateUser recUpdateUser);

	List<UserEntity> list();

	void deleteLogically(@NotNull String userId);

	
}
