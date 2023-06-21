package br.com.barber.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SuccessResponseDto {
    private String message;
    private Integer code;
    private Integer httpStatus;
}
