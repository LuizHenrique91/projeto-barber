package br.com.barber.exception.handler;

import br.com.barber.dto.error.ExceptionDto;
import br.com.barber.exception.CacheException;
import br.com.barber.exception.NotFoundException;
import br.com.barber.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestResponseExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> argumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> messages = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            messages.put(field, defaultMessage);
        });

        ExceptionDto exceptionDto = ExceptionDto.builder()
                .message(messages)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> conflict(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionDto.builder()
                .message(Map.of("Erro",ex.getMessage()))
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build());
    }

    @ExceptionHandler({BadRequestException.class,  IllegalArgumentException.class})
    public ResponseEntity<ExceptionDto> invalid(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionDto.builder()
                .message(Map.of("Erro",ex.getMessage()))
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build());
    }

    @ExceptionHandler(CacheException.class)
    public ResponseEntity<ExceptionDto> serverError(CacheException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionDto.builder()
                .message(Map.of("Erro",ex.getMessage()))
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build());
    }
}
