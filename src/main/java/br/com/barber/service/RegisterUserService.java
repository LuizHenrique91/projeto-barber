package br.com.barber.service;

import br.com.barber.dto.RegisterCredentialsDto;
import br.com.barber.dto.SuccessResponseDto;
import br.com.barber.dto.ValidationEmailDto;

import java.util.Map;
import java.util.UUID;

public interface RegisterUserService {
    Map<String, UUID> registerCredentials(RegisterCredentialsDto registerCredentialsDto);
    SuccessResponseDto createUser(ValidationEmailDto validationEmailDto);
    SuccessResponseDto resendCode(String id);
}
