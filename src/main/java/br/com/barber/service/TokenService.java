package br.com.barber.service;

import br.com.barber.model.UserCredentials;

public interface TokenService {
    Boolean isValid(String token);
    String generateToken(UserCredentials userCredentials);
}
