package br.com.barber.mapper;

import br.com.barber.dto.RegisterCredentialsDto;
import br.com.barber.model.UserCredentials;

public class UserCredentialsMapper {

    public static UserCredentials fromDtoToEntity(RegisterCredentialsDto registerCredentialsDto) {
        return UserCredentials.builder()
                .email(registerCredentialsDto.getEmail())
                .password(registerCredentialsDto.getPassword())
                .build();
    }
}
