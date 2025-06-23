package com.nulo.social.model.post;

import com.nulo.social.model.dto.RecPageRequest;
import jakarta.validation.constraints.NotNull;

public record RecListPost(
        @NotNull String body,
        @NotNull RecPageRequest pageRequest) {

}
