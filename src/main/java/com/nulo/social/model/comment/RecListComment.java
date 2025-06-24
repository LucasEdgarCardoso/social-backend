package com.nulo.social.model.comment;

import com.nulo.social.model.dto.RecPageRequest;

import jakarta.validation.constraints.NotNull;

public record RecListComment(
		@NotNull(message = "postId cannot be null") String postId,
		RecPageRequest pageRequest
) {
	
}
