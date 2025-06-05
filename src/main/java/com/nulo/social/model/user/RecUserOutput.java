package com.nulo.social.model.user;

import java.util.List;

public record RecUserOutput(
		String id,
		String name, 
		String email, 
		String bio,
		Boolean deleted
) {
	
	public RecUserOutput(UserEntity user) {
		this(user.getId().toHexString(), user.getName(), user.getEmail(), user.getBio(), user.getDeleted());
	}
	
	public static List<RecUserOutput> toRec(List<UserEntity> users) {
		return users.stream().map(RecUserOutput::new).toList();
	}

}
