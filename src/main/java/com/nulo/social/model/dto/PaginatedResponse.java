package com.nulo.social.model.dto;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;

public record PaginatedResponse<T>(
	    List<T> content,
	    int page,
	    int size,
	    long totalElements,
	    int totalPages
	) {
	    public <U> PaginatedResponse(Page<U> page, Function<U, T> mapper) {
	        this(
	            page.stream().map(mapper).toList(),
	            page.getNumber(),
	            page.getSize(),
	            page.getTotalElements(),
	            page.getTotalPages()
	        );
	    }
	}
