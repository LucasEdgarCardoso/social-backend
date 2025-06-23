package com.nulo.social.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nulo.social.model.comment.CommentEntity;

import jakarta.validation.constraints.NotNull;

@Repository
public interface CommentRepository extends MongoRepository<CommentEntity, ObjectId> {

	static final String FILTER_BY_ID_DELETED = "{ '_id': { $eq: ?0 } }";
	
	@Query(FILTER_BY_ID_DELETED)
	Optional<CommentEntity> findById(@NotNull ObjectId id);
	
}
