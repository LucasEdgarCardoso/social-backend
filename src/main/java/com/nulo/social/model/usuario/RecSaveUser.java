package com.nulo.social.model.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RecSaveUser(
		@NotNull(message = "Name cannot be null") String name, 
		
		@NotNull(message = "Email cannot be null") 
		@Email(message = "Email should be valid") 
		String email, 
		
		String bio
) {
	
	public static UserEntity toEntity(RecSaveUser recUser) {
		var user = new UserEntity();
		user.setName(recUser.name);
		user.setEmail(recUser.email);
		user.setBio(recUser.bio);
		return user;
	}
	
}
