package br.com.podologia.service.impl;

import br.com.podologia.dto.RegisterCredentialsDto;
import br.com.podologia.dto.SuccessResponseDto;
import br.com.podologia.dto.ValidationEmailDto;
import br.com.podologia.enums.ResponseMessage;
import br.com.podologia.exception.BadRequestException;
import br.com.podologia.mapper.UserCredentialsMapper;
import br.com.podologia.repository.CredenciaisRepository;
import br.com.podologia.service.RegisterUserService;
import br.com.podologia.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private CredenciaisRepository credenciaisRepository;

    @Autowired
    private AmazonSes amazonSes;

    @Autowired
    private CacheUtils cacheUtils;

    @Override
    public Map<String, UUID> registerCredentials(RegisterCredentialsDto registerCredentialsDto) {
        var existsEmail = credenciaisRepository.existsByEmail(registerCredentialsDto.getEmail());
        if(Boolean.TRUE.equals(existsEmail)){
            throw new BadRequestException("Cliente já cadastrao");
        }

        Integer validationCode = new Random().nextInt(1000, 9999);
        registerCredentialsDto.setCode(validationCode);

        UUID hashId = cacheUtils.setChache(registerCredentialsDto);

        amazonSes.sendEmail(registerCredentialsDto.getEmail(), validationCode);
        return Map.of("hashId", hashId);
    }

    @Override
    public SuccessResponseDto createUser(ValidationEmailDto validationEmailDto) {
        RegisterCredentialsDto cachedUser = cacheUtils.getCacheUser(validationEmailDto.getId());
        if(validationEmailDto.getCode().equals(cachedUser.getCode())){
            SuccessResponseDto response = this.saveUser(cachedUser);
            cacheUtils.cleanCache(validationEmailDto.getId());
            return response;
        }
        throw new BadRequestException("Código inválido");
    }

    @Override
    public SuccessResponseDto resendCode(String id) {
        RegisterCredentialsDto cachedUser = cacheUtils.getCacheUser(id);
        Integer validationCode = new Random().nextInt(1000, 9999);
        cachedUser.setCode(validationCode);
        cacheUtils.updateCache(id, cachedUser);
        amazonSes.sendEmail(cachedUser.getEmail(), validationCode);
        return SuccessResponseDto.builder()
                .message(ResponseMessage.SEND.getMessage())
                .code(ResponseMessage.SEND.getCode())
                .httpStatus(HttpStatus.OK.value())
                .build();
    }

    private SuccessResponseDto saveUser(RegisterCredentialsDto registerCredentialsDto) {
        credenciaisRepository.save(UserCredentialsMapper.fromDtoToEntity(registerCredentialsDto));
        return SuccessResponseDto.builder()
                .message(ResponseMessage.SUCCESS.getMessage())
                .code(ResponseMessage.SUCCESS.getCode())
                .httpStatus(HttpStatus.CREATED.value())
                .build();
    }
}
