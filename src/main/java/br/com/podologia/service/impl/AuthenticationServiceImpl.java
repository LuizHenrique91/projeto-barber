package br.com.podologia.service.impl;

import br.com.podologia.dto.AuthenticationDto;
import br.com.podologia.dto.TokenDto;
import br.com.podologia.exception.BadRequestException;
import br.com.podologia.exception.NotFoundException;
import br.com.podologia.model.UserCredentials;
import br.com.podologia.repository.CredenciaisRepository;
import br.com.podologia.service.AuthenticationService;
import br.com.podologia.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private CredenciaisRepository credenciaisRepository;

    @Autowired
    private TokenService tokenService;
    @Override
    public TokenDto auth(AuthenticationDto authenticationDto) {
        String token = tokenService.generateToken(getUser(authenticationDto));
        return TokenDto.builder()
                .type("HSMA")
                .token(token)
                .build();
    }

    private UserCredentials getUser(AuthenticationDto authenticationDto) {
        Optional<UserCredentials> userCredentialsOptional = credenciaisRepository.findByEmail(authenticationDto.getEmail());
        if (userCredentialsOptional.isEmpty()) {
            throw new NotFoundException("Cliente não existe");
        }
        UserCredentials credentials = userCredentialsOptional.get();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(authenticationDto.getPassword(), credentials.getPassword())){
            return credentials;
        }
        throw new BadRequestException("Usuário ou Senha inválidos");
    }
}
