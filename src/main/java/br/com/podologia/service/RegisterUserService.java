package br.com.podologia.service;

import br.com.podologia.dto.RegisterCredentialsDto;
import br.com.podologia.dto.SuccessResponseDto;
import br.com.podologia.dto.ValidationEmailDto;

import java.util.Map;
import java.util.UUID;

public interface RegisterUserService {
    Map<String, UUID> registerCredentials(RegisterCredentialsDto registerCredentialsDto);
    SuccessResponseDto createUser(ValidationEmailDto validationEmailDto);
    SuccessResponseDto resendCode(String id);
}
