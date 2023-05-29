package br.com.podologia.service;

import br.com.podologia.model.UserCredentials;

public interface TokenService {
    Boolean isValid(String token);
    String generateToken(UserCredentials userCredentials);
}
