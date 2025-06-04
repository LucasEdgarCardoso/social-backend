package com.nulo.social.model.user;

import java.util.Optional;

import jakarta.validation.constraints.NotNull;

public record RecUpdateUser(
		@NotNull String id,
		String name, 
		String bio
) {
	
	public static UserEntity updateValues(UserEntity user, RecUpdateUser recUser) {
		Optional.ofNullable(recUser)
			.map(RecUpdateUser::name)
			.ifPresent(newName -> user.setName(newName));
		Optional.ofNullable(recUser)
			.map(RecUpdateUser::bio)
			.ifPresent(newBio -> user.setBio(newBio));
		return user;
	}
	
}
