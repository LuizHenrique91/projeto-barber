package br.com.barber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Data
public class RegisterCredentialsDto {

    @Email(message = "E-mail deve ser válido")
    @NotBlank(message = "E-mail deve ser preenchido")
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8}$", message = "Senha fora do padrão desejado")
    @NotBlank(message = "Senha é obrigatória")
    private String password;

    private Integer code;
}
