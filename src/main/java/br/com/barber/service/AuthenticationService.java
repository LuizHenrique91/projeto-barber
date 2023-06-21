package br.com.barber.service;

import br.com.barber.dto.AuthenticationDto;
import br.com.barber.dto.TokenDto;

public interface AuthenticationService {

    TokenDto auth(AuthenticationDto authenticationDto);
}
