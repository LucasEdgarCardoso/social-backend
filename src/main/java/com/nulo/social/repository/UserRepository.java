package com.nulo.social.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nulo.social.model.user.UserEntity;

/**
 * Interface de repositório para operações com a coleção de usuários.
 */
@Repository
public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

	@Query("{ 'email': { $eq: ?0 }  }")
	Optional<UserEntity> findByEmail(String email);
	
}
