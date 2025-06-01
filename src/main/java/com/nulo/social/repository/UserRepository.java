package com.nulo.social.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nulo.social.model.usuario.UserEntity;

/**
 * Interface de repositório para operações com a coleção de usuários.
 */
@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    // Métodos de consulta personalizados podem ser adicionados aqui
}
