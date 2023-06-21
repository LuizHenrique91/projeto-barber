package br.com.barber.repository;

import br.com.barber.model.UserCredentials;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CredenciaisRepository extends MongoRepository<UserCredentials, String> {
    Boolean existsByEmail(String email);
    Optional<UserCredentials> findByEmail(String email);
}
