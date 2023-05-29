package br.com.podologia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
public class AuthenticationDto {

    @Email(message = "E-mail deve ser válido")
    @NotBlank(message = "E-mail deve ser preenchido")
    private String email;

    @NotBlank(message = "Senha deve ser informada")
    private String password;
}
