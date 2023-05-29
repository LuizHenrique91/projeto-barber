package br.com.podologia.dto.error;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ExceptionDto {
    private Map<String, String> message;
    private Integer statusCode;
}
