package com.nulo.social.repository;

import com.nulo.social.model.post.PostEntity;
import com.nulo.social.model.user.UserEntity;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface de repositório para operações com a coleção de posts.
 */
@Repository
public interface PostRepository extends MongoRepository<PostEntity, ObjectId> {
	
	static final String FILTER_BY_ID_DELETED = "{ '_id': { $eq: ?0 }, 'deleted': false }";
	
	@Query(FILTER_BY_ID_DELETED)
	Optional<PostEntity> findById(@NotNull ObjectId id);
	
	@Query(FILTER_BY_ID_DELETED)
	@Update(pipeline = "{ $set: { 'body': ?1 'tags': ?2 } }")
	void update(ObjectId id, String body, String[] tags);

	@Query("{ '_id': { $eq: ?0 } }")
	@Update(pipeline = "{ $set: { 'deleted': true } }")
	void deleteLogically(ObjectId id);

	@Query("{ 'body': { $regex: ?0, $options: 'i' }, 'deleted': false }")
	Page<PostEntity> findByBodyPageable(String body, Pageable pageRequest);
}
