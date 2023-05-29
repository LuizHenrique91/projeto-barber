package br.com.podologia.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
       super(message);
    }
}
