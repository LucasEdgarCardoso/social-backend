package com.nulo.social.model.dto;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public record RecPageRequest(
		int page,
	    int size,
	    List<SortField> sort
	) {
	    public PageRequest toPageRequest() {
	        if (sort == null || sort.isEmpty()) {
	            return PageRequest.of(page, size);
	        }

	        Sort sortObj = Sort.by(
	            sort.stream()
	                .map(sf -> new Sort.Order(
	                    Sort.Direction.fromOptionalString(String.valueOf(sf.direction())).orElse(Sort.Direction.ASC),
	                    sf.field()
	                ))
	                .toList()
	        );

	        return PageRequest.of(page, size, sortObj);
	    }

	    public record SortField(
	        String field,
	        AscDesc direction
	    ) {}
	    
	    public enum AscDesc {
	    	ASC,
	    	DESC
	    }
	}
