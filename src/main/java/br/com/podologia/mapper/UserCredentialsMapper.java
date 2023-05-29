package br.com.podologia.mapper;

import br.com.podologia.dto.RegisterCredentialsDto;
import br.com.podologia.model.UserCredentials;

public class UserCredentialsMapper {

    public static UserCredentials fromDtoToEntity(RegisterCredentialsDto registerCredentialsDto) {
        return UserCredentials.builder()
                .email(registerCredentialsDto.getEmail())
                .password(registerCredentialsDto.getPassword())
                .build();
    }
}
