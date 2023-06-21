package br.com.barber.controller;

import br.com.barber.dto.AuthenticationDto;
import br.com.barber.dto.TokenDto;
import br.com.barber.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/login")
public class Authentication {

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping
    public ResponseEntity<TokenDto> auth(@RequestBody @Valid AuthenticationDto authenticationDto){
        return ResponseEntity.ok().body(authenticationService.auth(authenticationDto));
    }
}
