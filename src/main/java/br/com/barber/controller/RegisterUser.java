package br.com.barber.controller;

import br.com.barber.dto.RegisterCredentialsDto;
import br.com.barber.dto.SuccessResponseDto;
import br.com.barber.dto.ValidationEmailDto;
import br.com.barber.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/register")
@Validated
public class RegisterUser {

    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping(path = "/user")
    public ResponseEntity<Map<String, UUID>> registerCredentials(@RequestBody @Valid RegisterCredentialsDto registerCredentialsDto) {
        return ResponseEntity.ok().body(registerUserService.registerCredentials(registerCredentialsDto));
    }

    @PostMapping(path = "/validationEmail")
    public ResponseEntity<SuccessResponseDto> validationEmail(@RequestBody @Valid ValidationEmailDto validationEmailDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUserService.createUser(validationEmailDto));
    }

    @PostMapping(path = "resendCode/{id}")
    public ResponseEntity<SuccessResponseDto> resendCode(@PathVariable("id") String id){
        return ResponseEntity.ok().body(registerUserService.resendCode(id));
    }
}
