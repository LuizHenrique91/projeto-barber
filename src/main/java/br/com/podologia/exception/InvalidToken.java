package br.com.podologia.exception;

public class InvalidToken extends RuntimeException{

    public InvalidToken(String message){
        super(message);
    }
}
