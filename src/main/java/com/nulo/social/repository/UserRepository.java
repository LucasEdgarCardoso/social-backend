package com.nulo.social.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import com.nulo.social.model.user.UserEntity;

import jakarta.validation.constraints.NotNull;

/**
 * Interface de repositório para operações com a coleção de usuários.
 */
@Repository
public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {
	
	static final String FILTER_BY_ID_DELETED = "{ '_id': { $eq: ?0 }, 'deleted': false }";

	@Query("{ 'email': { $eq: ?0 } }")
	Optional<UserEntity> findByEmail(String email);
	
	@Query(FILTER_BY_ID_DELETED)
	Optional<UserEntity> findById(@NotNull ObjectId id);
	
	@Query(FILTER_BY_ID_DELETED)
	@Update(pipeline = "{ $set: { 'name': ?1, 'bio': ?2 } }")
	void update(ObjectId id, String name, String bio);

	@Query("{ '_id': { $eq: ?0 } }")
	@Update(pipeline = "{ $set: { 'deleted': true } }")
	void deleteLogically(ObjectId id);
	
	@Query("{ 'name': { $regex: ?0, $options: 'i' }, 'deleted': false }")
	Page<UserEntity> findByNamePageable(String name, Pageable pageRequest);
	
}
