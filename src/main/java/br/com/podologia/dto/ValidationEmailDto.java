package br.com.podologia.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ValidationEmailDto {

    @NotBlank(message = "Deve ser informado")
    private String id;

    @Min(value = 1000, message = "Deve conter 4 dígitos")
    @Max(value = 9999, message = "Deve conter 4 dígitos")
    @NotNull(message = "Não pode ser nulo")
    private Integer code;
}
