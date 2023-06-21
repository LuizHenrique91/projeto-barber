package br.com.barber.enums;

import lombok.Getter;

@Getter
public enum ResponseMessage {

    SUCCESS("Operação efetuada com Sucesso", 100),
    SEND("E-mail enviado", 101);

    private String message;
    private Integer code;
    ResponseMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
