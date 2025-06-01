package com.nulo.social.repository;

import com.nulo.social.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para operações com a coleção de usuários.
 */
@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    // Métodos de consulta personalizados podem ser adicionados aqui
}
