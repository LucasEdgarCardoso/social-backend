package com.nulo.social.services;

import java.util.List;

import com.nulo.social.model.usuario.RecSaveUser;
import com.nulo.social.model.usuario.UserEntity;

public interface UserService {

	UserEntity save(RecSaveUser recSaveUser);

	List<UserEntity> list();
	
}
