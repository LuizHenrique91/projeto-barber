package br.com.podologia.repository;

import br.com.podologia.model.UserCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CredenciaisRepository extends MongoRepository<UserCredentials, String> {
    Boolean existsByEmail(String email);
    Optional<UserCredentials> findByEmail(String email);
}
