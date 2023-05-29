package br.com.podologia.service;

import br.com.podologia.dto.AuthenticationDto;
import br.com.podologia.dto.TokenDto;

public interface AuthenticationService {

    TokenDto auth(AuthenticationDto authenticationDto);
}
