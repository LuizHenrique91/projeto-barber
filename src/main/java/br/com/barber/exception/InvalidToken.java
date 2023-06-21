package br.com.barber.exception;

public class InvalidToken extends RuntimeException{

    public InvalidToken(String message){
        super(message);
    }
}
