package com.nulo.social.model.user;

import com.nulo.social.model.dto.RecPageRequest;

import jakarta.validation.constraints.NotNull;

public record RecListUser(
		@NotNull String name,
		@NotNull RecPageRequest pageRequest
	) {

}
